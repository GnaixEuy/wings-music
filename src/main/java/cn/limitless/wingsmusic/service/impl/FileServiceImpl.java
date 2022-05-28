package cn.limitless.wingsmusic.service.impl;

import cn.limitless.wingsmusic.dto.file.FileDto;
import cn.limitless.wingsmusic.dto.file.FileUploadDto;
import cn.limitless.wingsmusic.dto.file.FileUploadRequest;
import cn.limitless.wingsmusic.entity.File;
import cn.limitless.wingsmusic.enums.FileStatus;
import cn.limitless.wingsmusic.enums.Storage;
import cn.limitless.wingsmusic.exception.BizException;
import cn.limitless.wingsmusic.exception.ExceptionType;
import cn.limitless.wingsmusic.mapper.file.FileMapper;
import cn.limitless.wingsmusic.repository.FileRepository;
import cn.limitless.wingsmusic.service.FileService;
import cn.limitless.wingsmusic.service.StorageService;
import cn.limitless.wingsmusic.utils.FileTypeTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/18
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Service
public class FileServiceImpl extends BaseService implements FileService {

    private Map<String, StorageService> storageServices;

    private FileRepository repository;

    private FileMapper mapper;

    /**
     * 上传前初始化信息
     *
     * @param fileUploadRequest 上传对象信息模型
     * @return 返回上传文件对象穿梭模型
     * @throws IOException 输入输出流异常
     */
    @Override
    @Transactional
    public FileUploadDto initUpload(FileUploadRequest fileUploadRequest) throws IOException {
        // 创建File实体
        File file = mapper.createEntity(fileUploadRequest);
        System.out.println(getCurrentUserEntity());
        file.setType(FileTypeTransformer.getFileTypeFromExt(fileUploadRequest.getExt()));
        file.setStorage(getDefaultStorage());
        file.setCreatedBy(getCurrentUserEntity());
        file.setUpdatedBy(getCurrentUserEntity());
        File savedFile = repository.save(file);
        // 通过接口获取STS令牌
        FileUploadDto fileUploadDto = storageServices.get(getDefaultStorage().name()).initFileUpload();

        fileUploadDto.setKey(savedFile.getKey());
        fileUploadDto.setFileId(savedFile.getId());
        return fileUploadDto;
    }

    /**
     * 上传结束后返回文件穿梭对象信息模型
     *
     * @param id 文件id
     * @return 返回文件穿梭文件模型信息
     */
    @Override
    public FileDto finishUpload(String id) {
        File file = getFileEntity(id);
        // Todo: 是否是SUPER_ADMIN
        if (!Objects.equals(file.getCreatedBy().getId(), getCurrentUserEntity().getId())) {
            throw new BizException(ExceptionType.FILE_NOT_PERMISSION);
        }

        // Todo: 验证远程文件是否存在

        file.setStatus(FileStatus.UPLOADED);
        return mapper.toDto(repository.save(file));
    }

    /**
     * 获取默认文件存储类型
     *
     * @return 文件存储类型枚举类
     */
    @Override
    public Storage getDefaultStorage() {
        return Storage.COS;
    }

    /**
     * 获取文件持久层信息模型
     *
     * @param id 文件id
     * @return 返回文件持久层信息模型
     */
    @Override
    public File getFileEntity(String id) {
        Optional<File> fileOptional = repository.findById(id);
        if (fileOptional.isEmpty()) {
            throw new BizException(ExceptionType.FILE_NOT_FOUND);
        }
        return fileOptional.get();
    }

    @Autowired
    public void setStorageServices(Map<String, StorageService> storageServices) {
        this.storageServices = storageServices;
    }

    @Autowired
    public void setRepository(FileRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(FileMapper mapper) {
        this.mapper = mapper;
    }

}
