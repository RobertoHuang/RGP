/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: BrowserSecurityConfig
 * Author:   HuangTaiHong
 * Date:     2018-02-22 下午 3:38
 * Description: 浏览器安全认证配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.social.security.SpringSocialConfigurer;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;
import roberto.growth.process.security.core.service.CustomerUserDetailsService;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * 〈一句话功能简述〉<br>
 * 〈浏览器安全认证配置〉
 *
 * @author HuangTaiHong
 * @create 2018-02-22
 * @since 1.0.0
 */
@Configuration
public class BrowserSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Resource
    private CustomerSecurityProperties customerSecurityProperties;


    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Resource(name = "customerAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler customerAuthenticationSuccessHandler;

    @Resource(name = "customerAuthenticationFailureHandler")
    private AuthenticationFailureHandler customerAuthenticationFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private InvalidSessionStrategy invalidSessionStrategy;

    @Autowired
    private SessionInformationExpiredStrategy sessionInformationExpiredStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(springSocialConfigurer)
                .and()
            .formLogin()
                // 配置登录页
                .loginPage(customerSecurityProperties.getBrowser().getSignInPage())
                // 配置登录成功处理
                .successHandler(customerAuthenticationSuccessHandler)
                // 配置登录失败处理
                .failureHandler(customerAuthenticationFailureHandler)
                // 配置登录处理请求
                .loginProcessingUrl(customerSecurityProperties.getBrowser().getFormLoginProcessUrl()).and()
            .authorizeRequests()
                .antMatchers(
                    SecurityConstants.STATIC_RESOURCE,
                    SecurityConstants.GENERATE_CAPTCHA_URL_NOT_INTERCEPT,
                    customerSecurityProperties.getBrowser().getSignInPage(),
                    customerSecurityProperties.getBrowser().getFormLoginProcessUrl(),
                    customerSecurityProperties.getBrowser().getMobileLoginProcessUrl()).permitAll()
                .anyRequest().authenticated().and()
            .sessionManagement()
                .invalidSessionStrategy(invalidSessionStrategy)
                .maximumSessions(customerSecurityProperties.getBrowser().getSession().getMaximumSessions())
                .maxSessionsPreventsLogin(customerSecurityProperties.getBrowser().getSession().isMaxSessionsPreventsLogin())
                .expiredSessionStrategy(sessionInformationExpiredStrategy).and().and()
            .logout()
                .logoutUrl(customerSecurityProperties.getBrowser().getLogoutUrl())
                .logoutSuccessHandler(logoutSuccessHandler)
                .deleteCookies("JSESSIONID").and()
            .csrf().disable();
    }
}