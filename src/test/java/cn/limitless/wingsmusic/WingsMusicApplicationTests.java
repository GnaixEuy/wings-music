package cn.limitless.wingsmusic;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles(value = "unit-test")
class WingsMusicApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("test");
    }

}
