package cn.limitless.wingsmusic.mapper;

import cn.limitless.wingsmusic.dto.user.UserDto;
import cn.limitless.wingsmusic.dto.user.UserUpdateRequest;
import cn.limitless.wingsmusic.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/12
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@Mapper(componentModel = "spring")
@Component
public interface UserMapper {

    /**
     * 持久层实体转化为dto穿梭对象
     *
     * @param user 持久层对象
     * @return 穿梭对象
     */
    UserDto toDto(User user);


    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

}
