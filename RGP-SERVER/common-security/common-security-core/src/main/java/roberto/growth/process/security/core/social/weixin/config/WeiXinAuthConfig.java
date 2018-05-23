/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeiXinAutoConfig
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 8:20
 * Description: 微信登录配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.weixin.config;

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
import roberto.growth.process.security.core.properties.WeiXinProperties;
import roberto.growth.process.security.core.social.weixin.connet.WeixinConnectionFactory;

/**
 * 〈一句话功能简述〉<br> 
 * 〈微信登录配置类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10 
 * @since 1.0.0
 */
@EnableSocial
@Configuration
@ConditionalOnProperty(prefix = "roberto.growth.process.security.social.weixin", name = "appId")
public class WeiXinAuthConfig extends SocialConfigurerAdapter {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Override
    public void addConnectionFactories(ConnectionFactoryConfigurer configurer, Environment environment) {
        WeiXinProperties weixinConfig = customerSecurityProperties.getSocial().getWeixin();
        WeixinConnectionFactory weixinConnectionFactory = new WeixinConnectionFactory(weixinConfig.getProviderId(), weixinConfig.getAppId(), weixinConfig.getAppSecret());
        configurer.addConnectionFactory(weixinConnectionFactory);
    }

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
        // 由RGPSpringSocialAutoConfiguration进行配置
        return null;
    }
}