/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQImpl
 * Author:   HuangTaiHong
 * Date:     2018-05-04 下午 5:19
 * Description: QQ API实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.qq.api.impl;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;
import roberto.growth.process.security.core.social.qq.api.QQ;
import roberto.growth.process.security.core.social.qq.api.QQUserInfo;

/**
 * 〈一句话功能简述〉<br>
 * 〈QQ API实现类〉
 * 〈对应SpringSocial步骤7 根据令牌获取用户信息〉
 *
 * @author HuangTaiHong
 * @create 2018-05-04
 * @since 1.0.0
 */
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    /** 获取OpenId **/
    private static final String QQ_URL_GET_OPENID = "https://graph.qq.com/oauth2.0/me?access_token=%s";
    /** 获取用户信息 **/
    private static final String QQ_URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /** 配置文件读取 **/
    private String appId;

    /** 通过QQ_URL_GET_OPENID返回 **/
    private String openId;

    public QQImpl(String accessToken, String appId) {
        // access_token作为查询参数来携带
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        String url = String.format(QQ_URL_GET_OPENID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        this.openId = StringUtils.substringBetween(result, "\"openid\":\"", "\"}");
    }

    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(QQ_URL_GET_USERINFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        try {
            QQUserInfo userInfo = ((JSONObject) JSONObject.parse(result)).toJavaObject(QQUserInfo.class);
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (Exception e) {
            throw new RuntimeException("获取用户信息失败", e);
        }
    }
}