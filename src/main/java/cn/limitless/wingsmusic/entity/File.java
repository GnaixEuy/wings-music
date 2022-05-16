package cn.limitless.wingsmusic.entity;

import cn.limitless.wingsmusic.enums.FileStatus;
import cn.limitless.wingsmusic.enums.FileType;
import cn.limitless.wingsmusic.enums.Storage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Objects;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/17
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class File extends TraceableBaseEntity {
    private String name;

    @Column(name = "file_key")
    private String key;

    private String ext;

    private Integer size;

    @Enumerated(EnumType.STRING)
    private FileType type;

    @Enumerated(EnumType.STRING)
    private Storage storage;

    @Enumerated(EnumType.STRING)
    private FileStatus status = FileStatus.UPLOADING;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        File file = (File) o;
        return getId() != null && Objects.equals(getId(), file.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
