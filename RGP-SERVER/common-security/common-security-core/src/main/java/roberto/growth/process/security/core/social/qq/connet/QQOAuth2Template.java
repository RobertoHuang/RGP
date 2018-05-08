/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQOAuth2Template
 * Author:   HuangTaiHong
 * Date:     2018-05-04 下午 7:15
 * Description: QQ Auth2Template
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.qq.connet;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * 〈一句话功能简述〉<br>
 * 〈QQ Auth2Template〉
 * 〈对应SpringSocial步骤7 通过用户验证登录和授权获取Access Token，为下一步获取用户的OpenID做准备〉
 *
 * @author HuangTaiHong
 * @create 2018-05-04
 * @since 1.0.0
 */
@Slf4j
public class QQOAuth2Template extends OAuth2Template {
    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseStr = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        log.info("【QQOAuth2Template】获取accessToke的响应：responseStr={}" + responseStr);
        // 如果成功返回，即可在返回包中获取到Access Token 如：
        // access_token=FE04************************CCE2&expires_in=7776000&refresh_token=88E4************************BE14
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseStr, "&");
        // 授权令牌 AccessToken
        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        // AccessToken的有效期，单位为秒
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
        // 在授权自动续期步骤中，获取新的AccessToken时需要提供的参数
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");
        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }
}