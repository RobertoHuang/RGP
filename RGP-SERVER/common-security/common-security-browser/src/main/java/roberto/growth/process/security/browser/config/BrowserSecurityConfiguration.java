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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import roberto.growth.process.common.utils.DatabaseUtils;
import roberto.growth.process.security.browser.handler.CustomerAuthenticationFailureHandler;
import roberto.growth.process.security.browser.handler.CustomerAuthenticationSuccessHandler;
import roberto.growth.process.security.core.authentication.RGPSecurityAuthenticationFilter;
import roberto.growth.process.security.core.authentication.RGPSecurityAuthenticationProvider;
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
    private UserDetailsService userDetailsService;

    @Resource
    private CustomerSecurityProperties customerSecurityProperties;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        RGPSecurityAuthenticationFilter rgpSecurityAuthenticationFilter = new RGPSecurityAuthenticationFilter();
        rgpSecurityAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        http.formLogin()
                // 配置登录页
                .loginPage(customerSecurityProperties.getBrowser().getLoginPage())
                // 配置登录成功处理
                .successHandler(authenticationSuccessHandler())
                // 配置登录失败处理
                .failureHandler(authenticationFailureHandler())
                // 配置登录处理请求
                .loginProcessingUrl(customerSecurityProperties.getBrowser().getLoginProcessingUrl()).and()
            .authorizeRequests()
                .antMatchers(
                        SecurityConstants.STATIC_RESOURCE,
                        SecurityConstants.GENERATE_CAPTCHA_URL_NOT_INTERCEPT,
                        customerSecurityProperties.getBrowser().getLoginPage(),
                        customerSecurityProperties.getBrowser().getLoginProcessingUrl()).permitAll()
                .anyRequest().authenticated().and()
            .rememberMe()
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(customerSecurityProperties.getBrowser().getRememberMeSeconds()).and()
            .addFilterBefore(verifyCaptchaFilter, AbstractPreAuthenticatedProcessingFilter.class)
            .addFilterAt(rgpSecurityAuthenticationFilter,verifyCaptchaFilter.getClass())
            .authenticationProvider(authenticationProvider())
            .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // 配置密码加密算法
        return new BCryptPasswordEncoder();
    }

    @Bean(name = "customerAuthenticationFailureHandler")
    public AuthenticationFailureHandler authenticationFailureHandler() {
        CustomerAuthenticationFailureHandler customerAuthenticationFailureHandler = new CustomerAuthenticationFailureHandler();
        customerAuthenticationFailureHandler.setDefaultFailureUrl(customerSecurityProperties.getBrowser().getLoginPage() + "?error=true");
        return customerAuthenticationFailureHandler;
    }

    @Bean(name = "customerAuthenticationSuccessHandler")
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        CustomerAuthenticationSuccessHandler customerAuthenticationSuccessHandler = new CustomerAuthenticationSuccessHandler();
        customerAuthenticationSuccessHandler.setDefaultTargetUrl("/home");
        customerAuthenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(true);
        return customerAuthenticationSuccessHandler;
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        RGPSecurityAuthenticationProvider authenticationProvider = new RGPSecurityAuthenticationProvider();
        return authenticationProvider;
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