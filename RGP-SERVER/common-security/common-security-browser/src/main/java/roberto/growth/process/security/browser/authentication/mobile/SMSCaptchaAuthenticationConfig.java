/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptchaAuthenticationConfig
 * Author:   HuangTaiHong
 * Date:     2018-04-26 下午 3:04
 * Description: 短信验证码校验配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.authentication.mobile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 * 〈短信验证码校验配置〉
 *
 * @author HuangTaiHong
 * @create 2018-04-26
 * @since 1.0.0
 */
@Component
public class SMSCaptchaAuthenticationConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Resource(name = "customerAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler customerAuthenticationSuccessHandler;

    @Resource(name = "customerAuthenticationFailureHandler")
    private AuthenticationFailureHandler customerAuthenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String filterUrl = customerSecurityProperties.getBrowser().getMobileLoginProcessUrl();
        SMSCaptchaAuthenticationFilter smsCaptchaAuthenticationFilter = new SMSCaptchaAuthenticationFilter(filterUrl);
        smsCaptchaAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(customerAuthenticationSuccessHandler);
        smsCaptchaAuthenticationFilter.setAuthenticationFailureHandler(customerAuthenticationFailureHandler);

        SMSCaptchaAuthenticationProvider smsCaptchaAuthenticationProvider = new SMSCaptchaAuthenticationProvider();
        SMSCaptchaAuthenticationUserDetailsService smsCaptchaAuthenticationUserDetailsService = new SMSCaptchaAuthenticationUserDetailsService();
        smsCaptchaAuthenticationUserDetailsService.setCustomerSecurityProperties(customerSecurityProperties);
        smsCaptchaAuthenticationProvider.setUserDetailsService(smsCaptchaAuthenticationUserDetailsService);

        http.authenticationProvider(smsCaptchaAuthenticationProvider)
                .addFilterAfter(smsCaptchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}