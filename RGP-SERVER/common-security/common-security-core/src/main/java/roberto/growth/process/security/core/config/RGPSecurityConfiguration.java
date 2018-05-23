/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPSecurityConfiguration
 * Author:   HuangTaiHong
 * Date:     2018-05-14 上午 10:58
 * Description: Spring Security安全配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
@EnableConfigurationProperties(CustomerSecurityProperties.class)
public class RGPSecurityConfiguration {
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 配置密码加密算法
        return new BCryptPasswordEncoder();
    }
}