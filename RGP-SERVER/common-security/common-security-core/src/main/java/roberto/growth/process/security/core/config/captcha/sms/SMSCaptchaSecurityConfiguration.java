/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptchaSecurityConfiguration
 * Author:   HuangTaiHong
 * Date:     2018/5/14 22:02
 * Description: 短信验证码配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config.captcha.sms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import roberto.growth.process.security.core.config.captcha.sms.authentication.SMSCaptchaAuthenticationProvider;
import roberto.growth.process.security.core.config.captcha.sms.authentication.SMSCaptchaAuthenticationUserDetailsService;
import roberto.growth.process.security.core.filter.SMSCaptchaAuthenticationFilter;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈短信验证码配置类〉
 *
 * @author HuangTaiHong
 * @create 2018/5/14
 * @since 1.0.0
 */
@Configuration
public class SMSCaptchaSecurityConfiguration extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        String filterUrl = customerSecurityProperties.getBrowser().getMobileLoginProcessUrl();
        SMSCaptchaAuthenticationFilter smsCaptchaAuthenticationFilter = new SMSCaptchaAuthenticationFilter(filterUrl);
        smsCaptchaAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        smsCaptchaAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        smsCaptchaAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);

        SMSCaptchaAuthenticationProvider smsCaptchaAuthenticationProvider = new SMSCaptchaAuthenticationProvider();
        SMSCaptchaAuthenticationUserDetailsService smsCaptchaAuthenticationUserDetailsService = new SMSCaptchaAuthenticationUserDetailsService();
        smsCaptchaAuthenticationUserDetailsService.setCustomerSecurityProperties(customerSecurityProperties);
        smsCaptchaAuthenticationProvider.setUserDetailsService(smsCaptchaAuthenticationUserDetailsService);

        http.authenticationProvider(smsCaptchaAuthenticationProvider).addFilterAfter(smsCaptchaAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}