package yu.com.test2;

import org.springframework.web.bind.annotation.*;

/**
 * @Classname TestController
 * @Date 2021/4/6 15:47
 * @Created by yufx
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/show")
    public TestPojo show(@RequestBody TestPojo testPojo) {
        System.out.println("show2----------------------------------"+testPojo);
        return testPojo;
    }

    @PostMapping("/show2")
    public TestPojo show2(@RequestBody TestPojo testPojo) {
        System.out.println("show2----------------------------------"+testPojo);
        String userId = testPojo.getUserId();
        System.out.println(userId);
        return testPojo;
    }
}

