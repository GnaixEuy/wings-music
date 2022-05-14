package cn.limitless.wingsmusic.service;

import cn.limitless.wingsmusic.dto.token.TokenCreateRequest;
import cn.limitless.wingsmusic.dto.user.UserCreateRequest;
import cn.limitless.wingsmusic.dto.user.UserDto;
import cn.limitless.wingsmusic.dto.user.UserUpdateRequest;
import cn.limitless.wingsmusic.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.awt.print.Pageable;

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

    UserDto get(String id);

    void delete(String id);

    UserDto update(String id, UserUpdateRequest userUpdateRequest);

    Page<UserDto> search(Pageable pageable);

    String createToken(TokenCreateRequest tokenCreateRequest);

    /**
     * 新增用户方法
     *
     * @param userCreateRequest 接收创建用户数据模型
     * @return 返回创建好后映射的穿梭对象
     */
    UserDto create(UserCreateRequest userCreateRequest);
}
