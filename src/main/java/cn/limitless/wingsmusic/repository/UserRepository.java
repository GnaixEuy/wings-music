package cn.limitless.wingsmusic.repository;

import cn.limitless.wingsmusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/**
 * @author gnaixeuy
 */
public interface UserRepository extends JpaRepository<User, String> {

    /**
     * 通过用户名查找用户实体
     *
     * @param username 用户名
     * @return 返回可选的的user实体对象
     */
    Optional<User> findByUsername(String username);
}