package cn.limitless.wingsmusic.service;

import cn.limitless.wingsmusic.dto.file.FileDto;
import cn.limitless.wingsmusic.dto.file.FileUploadDto;
import cn.limitless.wingsmusic.dto.file.FileUploadRequest;
import cn.limitless.wingsmusic.entity.File;
import cn.limitless.wingsmusic.enums.Storage;

import java.io.IOException;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/18
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public interface FileService {

    /**
     * 上传前初始化信息
     *
     * @param fileUploadRequest 上传对象信息模型
     * @return 返回上传文件对象穿梭模型
     * @throws IOException 输入输出流异常
     */
    FileUploadDto initUpload(FileUploadRequest fileUploadRequest) throws IOException;

    /**
     * 上传结束后返回文件穿梭对象信息模型
     *
     * @param id 文件id
     * @return 返回文件穿梭文件模型信息
     */
    FileDto finishUpload(String id);

    /**
     * 获取默认文件存储类型
     *
     * @return 文件存储类型枚举类
     */
    Storage getDefaultStorage();

    /**
     * 获取文件持久层信息模型
     *
     * @param id 文件id
     * @return 返回文件持久层信息模型
     */
    File getFileEntity(String id);

}
