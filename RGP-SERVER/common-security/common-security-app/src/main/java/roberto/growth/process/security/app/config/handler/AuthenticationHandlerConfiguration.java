/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPAuthenticationHandlerConfiguration
 * Author:   HuangTaiHong
 * Date:     2018-05-14 上午 10:27
 * Description: 处理器配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.app.config.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import roberto.growth.process.security.app.handler.RGPAuthenticationFailureHandler;
import roberto.growth.process.security.app.handler.RGPAuthenticationSuccessHandler;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈处理器配置类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-14 
 * @since 1.0.0
 */
@Configuration
public class AuthenticationHandlerConfiguration {
    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        RGPAuthenticationFailureHandler authenticationFailureHandler = new RGPAuthenticationFailureHandler();
        authenticationFailureHandler.setDefaultFailureUrl(customerSecurityProperties.getBrowser().getSignInPage() + "?error=true");
        return authenticationFailureHandler;
    }

    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        RGPAuthenticationSuccessHandler authenticationSuccessHandler = new RGPAuthenticationSuccessHandler();
        authenticationSuccessHandler.setDefaultTargetUrl("/home");
        authenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(true);
        authenticationSuccessHandler.setClientDetailsService(clientDetailsService);
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(new InMemoryTokenStore());
        authenticationSuccessHandler.setAuthorizationServerTokenServices(defaultTokenServices);
        return authenticationSuccessHandler;
    }
}