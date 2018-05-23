/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPCustomerUserDetailsService
 * Author:   HuangTaiHong
 * Date:     2018/5/21 21:44
 * Description: 自定义UserDetailsService
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import roberto.growth.process.feign.UserCenterFeignClient;
import roberto.growth.process.uc.api.exception.RGPUserCenterException;
import roberto.growth.process.uc.api.vo.domain.UserDetail;
import roberto.growth.process.uc.api.vo.request.GetUserByPhoneNumberRequest;
import roberto.growth.process.uc.api.vo.request.GetUserByUsernameRequest;
import roberto.growth.process.uc.api.vo.response.GetUserByPhoneNumberResponse;
import roberto.growth.process.uc.api.vo.response.GetUserByUsernameResponse;

/**
 * 〈一句话功能简述〉<br> 
 * 〈自定义UserDetailsService〉
 *
 * @author HuangTaiHong
 * @create 2018/5/21
 * @since 1.0.0
 */
@Service
public class RGPCustomerUserDetailsService implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCenterFeignClient userCenterFeignClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        GetUserByPhoneNumberRequest getUserByPhoneNumberRequest = new GetUserByPhoneNumberRequest();
        getUserByPhoneNumberRequest.setPhoneNumber("12345");
        GetUserByPhoneNumberResponse getUserByPhoneNumberResponse = null;
        try {
            getUserByPhoneNumberResponse = userCenterFeignClient.getUserByPhoneNumber(getUserByPhoneNumberRequest);
        } catch (RGPUserCenterException e) {
            e.printStackTrace();
        }
        System.out.println(getUserByPhoneNumberResponse);

        GetUserByUsernameRequest getUserByUsernameRequest = new GetUserByUsernameRequest();
        getUserByUsernameRequest.setUsername(username);
        GetUserByUsernameResponse getUserByUsernameResponse = userCenterFeignClient.getUserByUsername(getUserByUsernameRequest);
        UserDetail userDetail = getUserByUsernameResponse.getUserDetail();
        return new User(userDetail.getUsername(), passwordEncoder.encode("dreamT"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}