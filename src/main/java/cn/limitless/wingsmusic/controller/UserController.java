package cn.limitless.wingsmusic.controller;

import cn.limitless.wingsmusic.dto.user.UserCreateRequest;
import cn.limitless.wingsmusic.mapper.UserMapper;
import cn.limitless.wingsmusic.service.UserService;
import cn.limitless.wingsmusic.vo.ResponseResult;
import cn.limitless.wingsmusic.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/14
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    private UserService userService;
    private UserMapper userMapper;

    @PostMapping(value = {""})

    @RolesAllowed(value = {"ROLE_ADMIN"})
    ResponseResult<UserVo> create(@RequestBody @Validated UserCreateRequest userCreateRequest) {
        return ResponseResult.success(userMapper.toVo(userService.create(userCreateRequest)));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }
}
