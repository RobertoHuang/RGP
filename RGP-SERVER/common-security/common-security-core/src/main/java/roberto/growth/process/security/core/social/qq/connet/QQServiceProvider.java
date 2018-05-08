/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQServiceProvider
 * Author:   HuangTaiHong
 * Date:     2018-05-04 下午 7:14
 * Description: QQ服务提供商
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.qq.connet;

import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import roberto.growth.process.security.core.social.qq.api.QQ;
import roberto.growth.process.security.core.social.qq.api.impl.QQImpl;

/**
 * 〈一句话功能简述〉<br>
 * 〈QQ服务提供商〉
 *
 * @author HuangTaiHong
 * @create 2018-05-04
 * @since 1.0.0
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {
    private String appId;
    /** 获取授权码 **/
    private static final String QQ_URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";
    /** 获取令牌地址 **/
    private static final String QQ_URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";

    public QQServiceProvider(String appId, String appSecret) {
        super(new QQOAuth2Template(appId, appSecret, QQ_URL_AUTHORIZE, QQ_URL_ACCESS_TOKEN));
        this.appId = appId;
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken, appId);
    }
}