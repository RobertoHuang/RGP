/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptchaAuthenticationProvider
 * Author:   HuangTaiHong
 * Date:     2018/5/14 22:09
 * Description: 短信验证码验证服务
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config.captcha.sms.authentication;

import lombok.Setter;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.util.ObjectUtils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈短信验证码验证服务〉
 *
 * @author HuangTaiHong
 * @create 2018/5/14
 * @since 1.0.0
 */
@Setter
public class SMSCaptchaAuthenticationProvider implements AuthenticationProvider {
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        SMSCaptchaAuthenticationToken authenticationToken = (SMSCaptchaAuthenticationToken) authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());
        if (ObjectUtils.isEmpty(user)) {
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        } else {
            SMSCaptchaAuthenticationToken authenticationResult = new SMSCaptchaAuthenticationToken(user, authenticationToken.getCredentials(), user.getAuthorities());
            authenticationResult.setDetails(authenticationToken.getDetails());
            return authenticationResult;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SMSCaptchaAuthenticationToken.class.isAssignableFrom(authentication);
    }
}