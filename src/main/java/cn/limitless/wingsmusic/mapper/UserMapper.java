package cn.limitless.wingsmusic.mapper;

import cn.limitless.wingsmusic.dto.user.UserCreateRequest;
import cn.limitless.wingsmusic.dto.user.UserDto;
import cn.limitless.wingsmusic.dto.user.UserUpdateRequest;
import cn.limitless.wingsmusic.entity.User;
import cn.limitless.wingsmusic.vo.user.UserVo;
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

    /**
     * 更新对象映射，将请求的修改数据映射进持久层对象中
     *
     * @param user              持久层对象模型
     * @param userUpdateRequest 更新请求对象模型
     * @return 返回映射好的持久层对象entity模型
     */
    User updateEntity(@MappingTarget User user, UserUpdateRequest userUpdateRequest);

    /**
     * 穿梭对象转化为view object
     *
     * @param userDto 穿梭对象
     * @return 展示对象
     */
    UserVo toVo(UserDto userDto);

    /**
     * 创建用户对象模型映射为持久层对象
     *
     * @param userCreateRequest 创建用户对象模型
     * @return 持久层对象
     */
    User createEntity(UserCreateRequest userCreateRequest);

}
