package cn.limitless.wingsmusic.service;

import cn.limitless.wingsmusic.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/12
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
public interface UserService extends UserDetailsService {
    @Override
    User loadUserByUsername(String username);
}
