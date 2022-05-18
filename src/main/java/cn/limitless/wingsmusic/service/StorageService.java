package cn.limitless.wingsmusic.service;

import cn.limitless.wingsmusic.dto.file.FileUploadDto;

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
public interface StorageService {

    /**
     * 上传前准备
     *
     * @return 准备上传完成后的文件穿梭对象
     * @throws IOException io异常
     */
    FileUploadDto initFileUpload() throws IOException;

    /**
     * 获取文件的URI
     *
     * @param fileKey 文件的hash key
     * @return 文件URI地址
     */
    String getFileUri(String fileKey);
}
