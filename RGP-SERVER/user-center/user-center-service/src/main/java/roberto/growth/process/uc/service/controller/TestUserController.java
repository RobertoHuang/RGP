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

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roberto.growth.process.uc.service.entity.User;

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
    @RequestMapping(value = "/test/user")
    public ResponseEntity<User> getUserDetails() {
        User user = new User();
        user.setUsername("123");
        user.setPassword("456");
        return new ResponseEntity(user, HttpStatus.OK);
    }
}