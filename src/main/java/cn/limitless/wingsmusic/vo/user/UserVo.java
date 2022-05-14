package cn.limitless.wingsmusic.vo.user;

import cn.limitless.wingsmusic.enums.Gender;
import cn.limitless.wingsmusic.vo.BaseVo;
import lombok.*;

import java.util.List;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/14
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserVo extends BaseVo {

    private String username;

    private String nickname;

    private Gender gender;

    private Boolean locked;

    private Boolean enabled;

    private List<RoleVo> roles;
}
