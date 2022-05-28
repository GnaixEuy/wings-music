package cn.limitless.wingsmusic.repository;

import cn.limitless.wingsmusic.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/18
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public interface FileRepository extends JpaRepository<File, String> {
}
