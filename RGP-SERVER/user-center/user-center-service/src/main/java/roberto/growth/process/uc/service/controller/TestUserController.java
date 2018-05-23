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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
@RestController
public class TestUserController {
    @Autowired
    private UserService userService;

    @Value("${roberto}")
    private String robertoHHH;

    @RequestMapping("/hi")
    public String testAutoConfig() {
        System.out.println(robertoHHH);
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
        return new ResponseEntity( userService.getUserInfo(id), HttpStatus.OK);
    }
}