/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SecurityConfiguration
 * Author:   HuangTaiHong
 * Date:     2018-05-14 下午 4:12
 * Description: Spring Security安全配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.app.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Spring Security安全配置〉
 *
 * @author HuangTaiHong
 * @create 2018-05-14 
 * @since 1.0.0
 */
@Configuration
public class SecurityConfiguration  extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(
                        SecurityConstants.STATIC_RESOURCE,
                        SecurityConstants.GENERATE_CAPTCHA_URL_NOT_INTERCEPT,
                        customerSecurityProperties.getBrowser().getSignInPage(),
                        customerSecurityProperties.getBrowser().getFormLoginProcessUrl(),
                        customerSecurityProperties.getBrowser().getMobileLoginProcessUrl()).permitAll()
                .anyRequest().authenticated()
                .and()
            .csrf().disable();
    }
}