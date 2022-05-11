package cn.limitless.wingsmusic.repository;

import cn.limitless.wingsmusic.entity.User;
import cn.limitless.wingsmusic.enums.Gender;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * <img src="http://blog.GnaixEuy.cn/wp-content/uploads/2021/08/bug.jpeg"/>
 *
 * <p>项目： wings-music </p>
 *
 * @author GnaixEuy
 * @date 2022/5/11
 * @see <a href="https://github.com/GnaixEuy"> GnaixEuy的GitHub </a>
 */
@SpringBootTest
@ActiveProfiles("unit-test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void save() {
        User user = new User();
        user.setNickname("GnaixEuy");
        user.setUsername("admin");
        user.setPassword("2333");
        user.setEnabled(Boolean.TRUE);
        user.setGender(Gender.MALE);
        user.setLocked(Boolean.FALSE);
        User save = this.userRepository.save(user);
        Assertions.assertNotNull(save);
    }

    @Test
    public void find() {
        this.userRepository.findAll().forEach(System.out::println);
    }


}