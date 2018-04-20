/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserAuthController
 * Author:   HuangTaiHong
 * Date:     2018-04-18 下午 6:45
 * Description: 用户认证Controller
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.ua.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roberto.growth.process.ua.api.vo.request.JwtAuthenticationRequest;
import roberto.growth.process.ua.api.vo.response.JwtAuthenticationResponse;
import roberto.growth.process.ua.service.feign.UserCenterFeignClient;
import roberto.growth.process.uc.api.exception.RGPUserCenterException;
import roberto.growth.process.uc.api.vo.domain.UserDetail;
import roberto.growth.process.uc.api.vo.request.ValidateUserRequest;
import roberto.growth.process.uc.api.vo.response.ValidateUserResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈用户认证Controller〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18
 * @since 1.0.0
 */
@RestController
@RequestMapping("/jwt")
public class UserAuthController {
    @Autowired
    private UserCenterFeignClient userCenterFeignClient;

    @PostMapping(value = "/token")
    public JwtAuthenticationResponse createAuthenticationToken(JwtAuthenticationRequest jwtAuthenticationRequest) throws RGPUserCenterException {
        ValidateUserRequest validateUserRequest = new ValidateUserRequest();
        validateUserRequest.setUsername(jwtAuthenticationRequest.getUsername());
        validateUserRequest.setPassword(jwtAuthenticationRequest.getPassword());
        ValidateUserResponse validateUserResponse = userCenterFeignClient.validateUser(validateUserRequest);
        UserDetail userDetail = validateUserResponse.getUserDetail();

        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setToken("123456");
        return jwtAuthenticationResponse;
    }
}