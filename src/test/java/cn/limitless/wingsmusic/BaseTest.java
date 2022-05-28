package cn.limitless.wingsmusic;

import cn.limitless.wingsmusic.dto.user.UserCreateRequest;
import cn.limitless.wingsmusic.dto.user.UserDto;
import cn.limitless.wingsmusic.entity.User;
import cn.limitless.wingsmusic.enums.Gender;
import cn.limitless.wingsmusic.repository.UserRepository;
import cn.limitless.wingsmusic.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/18
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@SpringBootTest
@ActiveProfiles(value = "unit-test")
public abstract class BaseTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @BeforeEach
    void setDefaultUser() {
        UserCreateRequest userCreateRequest = new UserCreateRequest();
        userCreateRequest.setUsername("admin_test");
        userCreateRequest.setNickname("GnaixEuy65535");
        userCreateRequest.setPassword("dddddd");
        userCreateRequest.setGender(Gender.MALE);
        UserDto userDto = userService.create(userCreateRequest);
        User user = userService.loadUserByUsername(userDto.getUsername());
        userRepository.save(user);
    }
}
