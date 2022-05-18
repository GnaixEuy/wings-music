package cn.limitless.wingsmusic.service.impl;

import cn.limitless.wingsmusic.dto.file.FileUploadDto;
import cn.limitless.wingsmusic.service.StorageService;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/17
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public class CosStorageServiceImpl implements StorageService {

    @Value("${cos.bucket}")
    private String bucket;

    @Value("${cos.secret.id}")
    private String secretId;

    @Value("${cos.secret.key}")
    private String secretKey;

    @Value("${cos.region}")
    private String region;

    @Override
    public FileUploadDto initFileUpload() throws IOException {
        return null;
    }

    @Override
    public String getFileUri(String fileKey) {
        return null;
    }
}
