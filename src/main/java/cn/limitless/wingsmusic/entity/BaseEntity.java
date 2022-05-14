package cn.limitless.wingsmusic.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/11
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString(onlyExplicitlyIncluded = true)
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @ToString.Include
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.AUTO, generator = "ksuid")
    @Column(nullable = false)
    @GenericGenerator(name = "ksuid", strategy = "cn.limitless.wingsmusic.utils.KsuidIdentifierGenerator")
    private String id;

    @ToString.Include
    @CreationTimestamp
    private Date createdTime;

    @ToString.Include
    @UpdateTimestamp
    private Date updatedTime;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

