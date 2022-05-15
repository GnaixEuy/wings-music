package cn.limitless.wingsmusic.controller;

import cn.limitless.wingsmusic.config.WebSecurityConfig;
import cn.limitless.wingsmusic.dto.user.UserCreateRequest;
import cn.limitless.wingsmusic.dto.user.UserUpdateRequest;
import cn.limitless.wingsmusic.mapper.UserMapper;
import cn.limitless.wingsmusic.service.UserService;
import cn.limitless.wingsmusic.vo.ResponseResult;
import cn.limitless.wingsmusic.vo.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import java.awt.print.Pageable;

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

    @DeleteMapping(value = {"/{id}"})
    @RolesAllowed(value = {"ROLE_ADMIN"})
    ResponseResult<String> delete(@RequestBody @PathVariable String id) {
        try {
            this.userService.delete(id);
        } catch (Exception e) {
            return ResponseResult.error("");
        }
        return ResponseResult.success("");
    }

    @PutMapping(value = {"/{id}"})
    @RolesAllowed(value = {"ROLE_ADMIN"})
    ResponseResult<UserVo> update(@PathVariable String id,
                                  @Validated @RequestBody UserUpdateRequest userUpdateRequest) {
        UserVo userVo = this.userMapper.toVo(this.userService.update(id, userUpdateRequest));
        return ResponseResult.success(userVo);
    }

    @GetMapping(value = {""})
    @RolesAllowed(value = {"ROLE_ADMIN"})
    ResponseResult<Page<UserVo>> search(@PageableDefault(sort = {"createdTime"}, direction = Sort.Direction.ASC) Pageable pageable) {
        Page<UserVo> userVoPage = userService.search(pageable).map(this.userMapper::toVo);
        return ResponseResult.success(userVoPage);
    }

    @GetMapping(value = {"/me"})
    ResponseResult<UserVo> me(HttpServletRequest httpServletRequeste) {
        String header = httpServletRequeste.getHeader(WebSecurityConfig.HEADER_STRING);
        return ResponseResult.success(this.userMapper.toVo(this.userService.getCurrentUser(header)));
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
