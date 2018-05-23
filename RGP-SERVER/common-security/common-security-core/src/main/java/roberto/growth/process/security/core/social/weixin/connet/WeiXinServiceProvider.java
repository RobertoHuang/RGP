/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeiXinServiceProvider
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 8:02
 * Description: WeixinServiceProvider连接服务提供商
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.weixin.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import roberto.growth.process.security.core.social.weixin.api.WeiXinImpl;
import roberto.growth.process.security.core.social.weixin.api.WeiXin;

/**
 * 〈一句话功能简述〉<br> 
 * 〈WeixinServiceProvider连接服务提供商〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10 
 * @since 1.0.0
 */
public class WeiXinServiceProvider extends AbstractOAuth2ServiceProvider<WeiXin> {
    /** 微信获取授权码的URL **/
    private static final String WEIXIN_URL_AUTHORIZE = "https://open.weixin.qq.com/connect/qrconnect";

    /** 微信获取AccessToken的URL(微信在获取accessToken时也已经返回openId) **/
    private static final String WEIXIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token";

    public WeiXinServiceProvider(String appId, String appSecret) {
        super(new WeiXinOAuth2Template(appId, appSecret, WEIXIN_URL_AUTHORIZE, WEIXIN_URL_ACCESS_TOKEN));
    }

    @Override
    public WeiXin getApi(String accessToken) {
        return new WeiXinImpl(accessToken);
    }
}