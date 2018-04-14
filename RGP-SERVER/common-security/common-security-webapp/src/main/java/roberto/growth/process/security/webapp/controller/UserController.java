/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserController
 * Author:   HuangTaiHong
 * Date:     2018-02-14 上午 10:29
 * Description: 用户相关Controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.webapp.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import roberto.growth.process.common.exception.RGPBaseException;
import roberto.growth.process.security.webapp.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户相关Controller〉
 *
 * @author HuangTaiHong
 * @create 2018-02-14
 * @since 1.0.0
 */
@RestController
public class UserController {
    @PostMapping("/user")
    public List<User> userList(@RequestBody User user33) throws RGPBaseException {
        try {
            testException2();
        } catch (Exception e) {
            throw new RGPBaseException("111", "jhdfhsdfds", e);
        }

        User user = new User("roberto", "dreamT");
        User user2 = new User("roberto", "dreamT");
        User user3 = new User("roberto", "dreamT");

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user2);
        userList.add(user3);

        return userList;
    }

    public void testException() throws ClassNotFoundException {
        try {
            System.out.println(1 / 0);
        } catch (Exception e) {
            ClassNotFoundException classNotFoundException = new ClassNotFoundException("111", e);
            throw classNotFoundException;
        }
    }

    public void testException2() throws RGPBaseException {
        try {
            testException();
        } catch (Exception e) {
            RuntimeException connectException = new RuntimeException("123123", e);
            throw connectException;
        }
    }
}