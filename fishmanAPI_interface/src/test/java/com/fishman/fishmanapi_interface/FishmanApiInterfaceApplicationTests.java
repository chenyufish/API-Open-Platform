package com.fishman.fishmanapi_interface;
import com.fishman.fishmanapi_client_sdk.client.FishmanAPIClient;
import com.fishman.fishmanapi_client_sdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * 测试类
 *
 */
@SpringBootTest
class FishmanAPIInterfaceApplicationTests {
    @Resource
    private FishmanAPIClient fishmanAPIClient;
    @Test
    void contextLoads() {
        String result = fishmanAPIClient.getNameByGet("yupi");
        User user = new User();
        user.setUsername("fishmanApi");
        String usernameByPost = fishmanAPIClient.getUsernameByPost(user);
        System.out.println(result);
        System.out.println(usernameByPost);
    }

}
