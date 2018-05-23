/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: WeiXinOAuth2Template
 * Author:   HuangTaiHong
 * Date:     2018-05-10 下午 7:53
 * Description: WeiXin OAuth2Template
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.weixin.connet;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈WeiXin OAuth2Template〉
 * 〈对应SpringSocial步骤7 处理微信返回的令牌信息〉
 *
 * @author HuangTaiHong
 * @create 2018-05-10
 * @since 1.0.0
 */
@Slf4j
public class WeiXinOAuth2Template extends OAuth2Template {
    private String clientId;
    private String clientSecret;
    private String accessTokenUrl;
    private static final String REFRESH_TOKEN_URL = "https://api.weixin.qq.com/sns/oauth2/refresh_token";

    public WeiXinOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.accessTokenUrl = accessTokenUrl;
    }

    @Override
    public AccessGrant exchangeForAccess(String authorizationCode, String redirectUri, MultiValueMap<String, String> parameters) {
        StringBuilder accessTokenRequestUrl = new StringBuilder(accessTokenUrl);
        accessTokenRequestUrl.append("?appid=" + clientId);
        accessTokenRequestUrl.append("&secret=" + clientSecret);
        accessTokenRequestUrl.append("&code=" + authorizationCode);
        accessTokenRequestUrl.append("&grant_type=authorization_code");
        accessTokenRequestUrl.append("&redirect_uri=" + redirectUri);
        return getAccessToken(accessTokenRequestUrl);
    }

    @Override
    public AccessGrant refreshAccess(String refreshToken, MultiValueMap<String, String> additionalParameters) {
        StringBuilder refreshTokenUrl = new StringBuilder(REFRESH_TOKEN_URL);
        refreshTokenUrl.append("?appid=" + clientId);
        refreshTokenUrl.append("&grant_type=refresh_token");
        refreshTokenUrl.append("&refresh_token=" + refreshToken);
        return getAccessToken(refreshTokenUrl);
    }

    @SuppressWarnings("unchecked")
    private AccessGrant getAccessToken(StringBuilder accessTokenRequestUrl) {
        log.info("获取access_token, 请求URL: " + accessTokenRequestUrl.toString());
        String response = getRestTemplate().getForObject(accessTokenRequestUrl.toString(), String.class);
        log.info("获取access_token, 响应内容: " + response);
        Map<String, Object> result = null;
        try {
            result = ((JSONObject) JSONObject.parse(response)).toJavaObject(Map.class);
        } catch (Exception e) {
            // 返回错误码时直接返回空
            if (StringUtils.isNotBlank(MapUtils.getString(result, "errcode"))) {
                String errcode = MapUtils.getString(result, "errcode");
                String errmsg = MapUtils.getString(result, "errmsg");
                throw new RuntimeException("获取access token失败, errcode:" + errcode + ", errmsg:" + errmsg, e);
            }
        }

        WeiXinAccessGrant accessToken = new WeiXinAccessGrant(
                MapUtils.getString(result, "access_token"),
                MapUtils.getString(result, "scope"),
                MapUtils.getString(result, "refresh_token"),
                MapUtils.getLong(result, "expires_in"));

        accessToken.setOpenId(MapUtils.getString(result, "openid"));
        return accessToken;
    }

    @Override
    public String buildAuthorizeUrl(OAuth2Parameters parameters) {
        return buildAuthenticateUrl(parameters);
    }

    @Override
    public String buildAuthenticateUrl(OAuth2Parameters parameters) {
        // 构建获取授权码的请求 也就是引导用户跳转到微信的地址
        String url = super.buildAuthenticateUrl(parameters);
        url = url + "&appid=" + clientId + "&scope=snsapi_login";
        return url;
    }

    @Override
    protected RestTemplate createRestTemplate() {
        // 微信返回的contentType是html/text
        // 添加相应的HttpMessageConverter来处理
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}