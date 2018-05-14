/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: FormAuthenticationConfiguration
 * Author:   HuangTaiHong
 * Date:     2018/5/14 21:44
 * Description: 表单登陆配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.app.config.form;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

/**
 * 〈一句话功能简述〉<br>
 * 〈表单登陆配置〉
 *
 * @author HuangTaiHong
 * @create 2018/5/14
 * @since 1.0.0
 */
@Configuration
public class FormAuthenticationConfiguration {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Autowired
    protected AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler authenticationFailureHandler;

    public void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(customerSecurityProperties.getBrowser().getSignInPage())
                .loginProcessingUrl(customerSecurityProperties.getBrowser().getFormLoginProcessUrl())
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler);
    }
}