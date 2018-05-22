/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: HiController
 * Author:   HuangTaiHong
 * Date:     2018/5/21 22:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.acd.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author HuangTaiHong
 * @create 2018/5/21
 * @since 1.0.0
 */
@Slf4j
@RestController
public class HiController {
    @Value("${server.port}")
    private String port;

    @RequestMapping("/hi")
    public String home() {
        return "hi :" + ",i am from port:" + port;
    }

    @RequestMapping("/me")
    public Object getCurrentUser(Authentication userDetails) {
        return userDetails;
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @RequestMapping("/hello")
    public String hello() {
        return "hello you!";
    }

    @GetMapping("/getPrinciple")
    public OAuth2Authentication getPrinciple(OAuth2Authentication oAuth2Authentication, Principal principal, Authentication authentication) {
        log.info(oAuth2Authentication.getUserAuthentication().getAuthorities().toString());
        log.info(oAuth2Authentication.toString());
        log.info("principal.toString()" + principal.toString());
        log.info("principal.getName()" + principal.getName());
        log.info("authentication:" + authentication.getAuthorities().toString());
        return oAuth2Authentication;
    }
}