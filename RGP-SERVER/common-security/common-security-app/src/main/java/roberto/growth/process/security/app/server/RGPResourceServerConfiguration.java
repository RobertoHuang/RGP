/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPResourceServerConfiguration
 * Author:   HuangTaiHong
 * Date:     2018-05-14 下午 5:07
 * Description: 资源服务器配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.app.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.social.security.SpringSocialConfigurer;
import roberto.growth.process.security.app.config.form.FormAuthenticationConfiguration;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

/**
 * 〈一句话功能简述〉<br>
 * 〈资源服务器配置〉
 *
 * @author HuangTaiHong
 * @create 2018-05-14
 * @since 1.0.0
 */
@Configuration
@EnableResourceServer
public class RGPResourceServerConfiguration extends ResourceServerConfigurerAdapter {
//    @Autowired
//    private OpenIdAuthenticationSecurityConfig openIdAuthenticationSecurityConfig;

//    @Autowired
//    private AuthorizeConfigManager authorizeConfigManager;

    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Autowired
    /** Social配置 **/
    private SpringSocialConfigurer springSocialConfigurer;

    @Autowired
    /** 表单登陆相关配置 **/
    private FormAuthenticationConfiguration formAuthenticationConfiguration;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        formAuthenticationConfiguration.configure(http);
        http
            .apply(springSocialConfigurer)
                .and()
            .authorizeRequests()
                .antMatchers(
                        SecurityConstants.STATIC_RESOURCE,
                        SecurityConstants.GENERATE_CAPTCHA_URL_NOT_INTERCEPT,
                        customerSecurityProperties.getBrowser().getSignInPage(),
                        customerSecurityProperties.getBrowser().getFormLoginProcessUrl(),
                        customerSecurityProperties.getBrowser().getMobileLoginProcessUrl()).permitAll()
                .anyRequest().authenticated().and()
//            .apply(openIdAuthenticationSecurityConfig)
//                .and()
            .csrf().disable();

//        authorizeConfigManager.config(http.authorizeRequests());
    }
}