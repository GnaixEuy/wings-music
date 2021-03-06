package cn.limitless.wingsmusic.controller;

import cn.limitless.wingsmusic.vo.ResponseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/12
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@RestController
@RequestMapping(value = {"/hello"})
public class HelloController {

    @GetMapping(value = {""})
    public ResponseResult<String> test() {
        return ResponseResult.success("开始项目吧! Wings of Music!");
    }

}
