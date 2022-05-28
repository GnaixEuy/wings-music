package cn.limitless.wingsmusic.vo.file;

import cn.limitless.wingsmusic.enums.FileStatus;
import cn.limitless.wingsmusic.enums.FileType;
import cn.limitless.wingsmusic.enums.Storage;
import cn.limitless.wingsmusic.vo.BaseVo;
import lombok.*;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/18
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileVo extends BaseVo {

    private String name;

    private String key;

    private String uri;

    private Storage storage;

    private String ext;

    private Long size;

    private FileType type;

    private FileStatus status;

}
