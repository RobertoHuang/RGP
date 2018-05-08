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
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.social.security.SpringSocialConfigurer;
import roberto.growth.process.common.utils.DatabaseUtils;
import roberto.growth.process.security.browser.authentication.mobile.SMSCaptchaAuthenticationConfig;
import roberto.growth.process.security.browser.service.CustomerUserDetailsService;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

import javax.annotation.Resource;
import javax.servlet.Filter;
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
@EnableConfigurationProperties(CustomerSecurityProperties.class)
public class BrowserSecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource(name = "verifyCaptchaFilter")
    private Filter verifyCaptchaFilter;

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CustomerUserDetailsService customerUserDetailsService;

    @Resource
    private CustomerSecurityProperties customerSecurityProperties;

    @Autowired
    private SMSCaptchaAuthenticationConfig smsCaptchaAuthenticationConfig;

    @Autowired
    private SpringSocialConfigurer springSocialConfigurer;

    @Resource(name = "customerAuthenticationSuccessHandler")
    private AuthenticationSuccessHandler customerAuthenticationSuccessHandler;

    @Resource(name = "customerAuthenticationFailureHandler")
    private AuthenticationFailureHandler customerAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.apply(smsCaptchaAuthenticationConfig)
                .and()
            .apply(springSocialConfigurer)
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
            .rememberMe()
                .userDetailsService(customerUserDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(customerSecurityProperties.getBrowser().getRememberMeSeconds()).and()
            .addFilterBefore(verifyCaptchaFilter, AbstractPreAuthenticatedProcessingFilter.class)
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 配置密码加密算法
        return new BCryptPasswordEncoder();
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果保存Token的表不存在则创建
        if (!DatabaseUtils.isTableExist(dataSource, "persistent_logins")) {
            tokenRepository.setCreateTableOnStartup(true);
        }
        return tokenRepository;
    }
}