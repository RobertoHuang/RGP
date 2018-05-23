/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQAuthConfig
 * Author:   HuangTaiHong
 * Date:     2018-05-08 上午 11:09
 * Description: QQ登录配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.qq.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.social.config.annotation.ConnectionFactoryConfigurer;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;
import roberto.growth.process.security.core.properties.QQProperties;
import roberto.growth.process.security.core.social.qq.connet.QQConnectionFactory;

/**
 * 〈一句话功能简述〉<br>
 * 〈QQ登录配置类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-08
 * @since 1.0.0
 */
@EnableSocial
@Configuration
@ConditionalOnProperty(prefix = "roberto.growth.process.security.social.qq", name = "appId")
public class QQAuthConfig extends SocialConfigurerAdapter {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        QQProperties qqProperties = customerSecurityProperties.getSocial().getQq();
        QQConnectionFactory qqConnectionFactory = new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
        configurer.addConnectionFactory(qqConnectionFactory);
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // 由RGPSpringSocialAutoConfiguration进行配置
        return null;
    }
}