/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPAuthenticationSuccessHandler
 * Author:   HuangTaiHong
 * Date:     2018-05-14 上午 10:28
 * Description: 校验成功处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.app.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.Setter;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈校验成功处理器〉
 *
 * @author HuangTaiHong
 * @create 2018-05-14
 * @since 1.0.0
 */
@Setter
public class RGPAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private ClientDetailsService clientDetailsService;
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    @SuppressWarnings("unchecked")
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        logger.info("登录成功");
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        } else {
            String[] tokens = this.extractAndDecodeHeader(header, request);
            assert tokens.length == 2;
            String clientId = tokens[0];
            String clientSecret = tokens[1];
            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
            if (clientDetails == null) {
                throw new UnapprovedClientAuthenticationException("clientId对应的配置信息不存在:" + clientId);
            } else if (!StringUtils.equals(clientDetails.getClientSecret(), clientSecret)) {
                throw new UnapprovedClientAuthenticationException("clientSecret不匹配:" + clientId);
            }
            TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken token = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(token));
        }
    }

    @SuppressWarnings("all")
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request) throws IOException {
        try {
            String basic = header.substring(6);
            byte[] base64Token = basic.getBytes("UTF-8");

            byte[] decoded = Base64.decode(base64Token);
            String token = new String(decoded, "UTF-8");
            int delim = token.indexOf(":");
            if (delim == -1) {
                throw new BadCredentialsException("Invalid basic authentication token");
            }
            return new String[]{token.substring(0, delim), token.substring(delim + 1)};
        } catch (IllegalArgumentException e) {
            throw new BadCredentialsException("Failed to decode basic authentication token");
        }
    }
}