/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPSecurityAuthenticationProvider
 * Author:   HuangTaiHong
 * Date:     2018-04-04 下午 2:36
 * Description: RGP用户验证服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import roberto.growth.process.security.core.authentication.RGPSecurityAuthenticationToken.AuthType;
import roberto.growth.process.security.core.entity.RGPSecurityUserDetails;

/**
 * 〈一句话功能简述〉<br>
 * 〈RGP用户验证服务〉
 *
 * @author HuangTaiHong
 * @create 2018-04-04
 * @since 1.0.0
 */
public class RGPSecurityAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        RGPSecurityAuthenticationToken authenticationToken = (RGPSecurityAuthenticationToken) authentication;
        if (AuthType.PASSWORD == authenticationToken.getAuthType()) {
            authEamilPassword(userDetails, authenticationToken);
        }
    }

    private void authEamilPassword(UserDetails userDetails, RGPSecurityAuthenticationToken authenticationToken) {
        if (authenticationToken.getCredentials() == null) {
            throw new BadCredentialsException("未提供有效身份凭证");
        } else {
            String presentedPassword = authenticationToken.getCredentials().toString();
            if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                throw new BadCredentialsException("用户名或密码不正确");
            }
        }
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
        RGPSecurityAuthenticationToken authenticationToken = (RGPSecurityAuthenticationToken) authentication;
        if (AuthType.SMSCAPTCHA == authenticationToken.getAuthType()) {
            return new RGPSecurityUserDetails();
        } else {
            // TODO
            return new RGPSecurityUserDetails();
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return RGPSecurityAuthenticationToken.class.isAssignableFrom(authentication);
    }
}