package cn.limitless.wingsmusic.repository;

import cn.limitless.wingsmusic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * @author gnaixeuy
 */
public interface UserRepository extends JpaRepository<User, String> {
}