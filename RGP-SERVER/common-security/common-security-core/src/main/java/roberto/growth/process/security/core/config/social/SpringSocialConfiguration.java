/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SpringSocialConfiguration
 * Author:   HuangTaiHong
 * Date:     2018/5/14 22:19
 * Description: SpringSocial安全配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.social.security.SpringSocialConfigurer;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;
import roberto.growth.process.security.core.social.CustomerSpringSocialConfiguration;

import javax.sql.DataSource;

/**
 * 〈一句话功能简述〉<br> 
 * 〈SpringSocial安全配置〉
 *
 * @author HuangTaiHong
 * @create 2018/5/14
 * @since 1.0.0
 */
@Configuration
public class SpringSocialConfiguration extends SocialConfigurerAdapter {
    @Autowired
    private DataSource dataSource;

    @Autowired(required = false)
    private ConnectionSignUp connectionSignUp;

    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Bean
    public SpringSocialConfigurer springSocialConfigurer() {
        String filterProcessesUrl = customerSecurityProperties.getSocial().getFilterProcessesUrl();
        CustomerSpringSocialConfiguration configurer = new CustomerSpringSocialConfiguration(filterProcessesUrl);
        configurer.signupUrl(customerSecurityProperties.getBrowser().getSignUpPage());
        return configurer;
    }

    @Bean
    public ConnectionSignUp connectionSignUp(){
        return new ConnectionSignUp() {
            @Override
            public String execute(Connection<?> connection) {
                return connection.getKey().getProviderUserId();
            }
        };
    }

    /**
     * 功能描述: <br>
     * 〈处理注册流程的工具类〉
     *
     * @param factoryLocator
     * @return org.springframework.social.connect.web.ProviderSignInUtils
     * @author HuangTaiHong
     * @date 2018.05.08 20:35:41
     */
    @Bean
    public ProviderSignInUtils providerSignInUtils(ConnectionFactoryLocator factoryLocator) {
        return new ProviderSignInUtils(factoryLocator, getUsersConnectionRepository(factoryLocator));
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
        if (connectionSignUp != null) {
            repository.setConnectionSignUp(connectionSignUp);
        }
        return repository;
    }

    @Override
    public UserIdSource getUserIdSource() {
        return new UserIdSource() {
            @Override
            public String getUserId() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication == null) {
                    throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
                }
                return authentication.getName();
            }
        };
    }
}