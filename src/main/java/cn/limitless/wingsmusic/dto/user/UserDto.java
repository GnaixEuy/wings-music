package cn.limitless.wingsmusic.dto.user;

import cn.limitless.wingsmusic.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.Date;
import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/12
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    private String id;
    private Date createdTime;
    private Date updatedTime;
    private String username;
    private String nickname;
    private String password;
    private Gender gender;
    private Boolean locked = false;
    private Boolean enabled = false;
    private String lastLoginIp;
    private Instant lastLoginTime;
    private String openId;
    private List<RoleDto> roles;
}
