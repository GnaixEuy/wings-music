package cn.limitless.wingsmusic.dto.user;

import cn.limitless.wingsmusic.enums.Gender;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/14
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
public class UserCreateRequest {

    @NotBlank(message = "用户名不能为空")
    @Size(min = 4, max = 64, message = "用户名长度应该在4个字符到64个字符之间")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Size(min = 6, max = 64, message = "密码长度应该在6个字符到64个字符之间")
    private String password;

    private String nickname;

    private Gender gender;

}