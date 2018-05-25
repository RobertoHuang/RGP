/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: TestUserController
 * Author:   HuangTaiHong
 * Date:     2018-04-19 下午 4:47
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.service.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roberto.growth.process.uc.service.entity.User;
import roberto.growth.process.uc.service.service.UserService;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author HuangTaiHong
 * @create 2018-04-19
 * @since 1.0.0
 */
@RefreshScope
@RestController
public class TestUserController {
    @Autowired
    private UserService userService;

    @Value("${roberto}")
    private String roberto;

    @RequestMapping("/hi")
    @HystrixCommand(fallbackMethod = "testAutoConfigFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000"),
            @HystrixProperty(name =  "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name =  "circuitBreaker.requestVolumeThreshold",value = "10"),
            @HystrixProperty(name =  "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),
            @HystrixProperty(name =  "circuitBreaker.errorThresholdPercentage",value = "50"),
    })
    public String testAutoConfig() throws InterruptedException {
        boolean flag = true;
        if (flag) {
            throw new RuntimeException("服务器忙");
        }
        System.out.println(roberto);
        return "";
    }

    @RequestMapping(value = "/test/user")
    public ResponseEntity<User> getUserDetails() {
        User user = new User();
        user.setUsername("123");
        user.setPassword("456");
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/get")
    public ResponseEntity<User> getUser() {
        String id = "41280540844257280";
        return new ResponseEntity(userService.getUserInfo(id), HttpStatus.OK);
    }

    public String testAutoConfigFallBack() {
        return "服务器忙 请稍后重试";
    }
}