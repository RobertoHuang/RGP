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

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .and()
            .authorizeRequests()
                .anyRequest().authenticated()
                .and()
            .csrf().disable();
    }
}