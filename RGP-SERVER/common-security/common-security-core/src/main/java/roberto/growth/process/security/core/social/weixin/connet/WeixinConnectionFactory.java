/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeixinConnectionFactory
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 8:06
 * Description: 微信连接服务提供商的工厂类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.weixin.connet;

import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionData;
import org.springframework.social.connect.support.OAuth2Connection;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2ServiceProvider;
import roberto.growth.process.security.core.social.weixin.api.WeiXin;

/**
 * 〈一句话功能简述〉<br> 
 * 〈微信连接服务提供商的工厂类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10 
 * @since 1.0.0
 */
public class WeixinConnectionFactory extends OAuth2ConnectionFactory<WeiXin> {
    public WeixinConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new WeiXinServiceProvider(appId, appSecret), new WeiXinAdapter());
    }

    /**
     * 由于微信的openId是和accessToken一起返回的
     * 所以在这里直接根据accessToken设置providerUserId即可，不用像QQ那样通过QQAdapter来获取
     */
    @Override
    protected String extractProviderUserId(AccessGrant accessGrant) {
        if(accessGrant instanceof WeiXinAccessGrant) {
            return ((WeiXinAccessGrant)accessGrant).getOpenId();
        }
        return null;
    }

    @Override
    public Connection<WeiXin> createConnection(AccessGrant accessGrant) {
        return new OAuth2Connection(getProviderId(), extractProviderUserId(accessGrant), accessGrant.getAccessToken(), accessGrant.getRefreshToken(), accessGrant.getExpireTime(), getOAuth2ServiceProvider(), getApiAdapter(extractProviderUserId(accessGrant)));
    }

    @Override
    public Connection<WeiXin> createConnection(ConnectionData data) {
        return new OAuth2Connection(data, getOAuth2ServiceProvider(), getApiAdapter(data.getProviderUserId()));
    }

    private ApiAdapter<WeiXin> getApiAdapter(String providerUserId) {
        return new WeiXinAdapter(providerUserId);
    }

    private OAuth2ServiceProvider<WeiXin> getOAuth2ServiceProvider() {
        return (OAuth2ServiceProvider<WeiXin>) getServiceProvider();
    }
}