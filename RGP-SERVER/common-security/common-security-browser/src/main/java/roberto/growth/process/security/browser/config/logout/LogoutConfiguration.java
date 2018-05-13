/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: LogoutConfiguration
 * Author:   HuangTaiHong
 * Date:     2018/5/13 11:49
 * Description: 退出登录配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.config.logout;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import roberto.growth.process.security.browser.config.logout.handler.RGPLogoutSuccessHandler;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈退出登录配置〉
 *
 * @author HuangTaiHong
 * @create 2018/5/13
 * @since 1.0.0
 */
@Configuration
public class LogoutConfiguration {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(LogoutSuccessHandler.class)
    public LogoutSuccessHandler logoutSuccessHandler(){
        return new RGPLogoutSuccessHandler(customerSecurityProperties.getBrowser().getLogoutSuccessPage());
    }
}