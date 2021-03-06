package cn.limitless.wingsmusic.controller;

import cn.limitless.wingsmusic.dto.token.TokenCreateRequest;
import cn.limitless.wingsmusic.service.UserService;
import cn.limitless.wingsmusic.vo.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 * TODO: 待完成登录接口及测试
 *
 * @author GnaixEuy
 * @date 2022/5/13
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */

@RestController
@RequestMapping(value = {"/token"})
public class TokenController {

    private UserService userService;

    @PostMapping(value = {"/login"})
    public ResponseResult<String> login(@RequestBody @Validated TokenCreateRequest tokenCreateRequest) {
        return ResponseResult.success(userService.createToken(tokenCreateRequest));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
