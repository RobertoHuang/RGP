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

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户相关Controller〉
 *
 * @author HuangTaiHong
 * @create 2018-02-14
 * @since 1.0.0
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @GetMapping("/me")
    public Object getCurrentUser(Authentication user, HttpServletRequest request) {
        return user;
    }
}