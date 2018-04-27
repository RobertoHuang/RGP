/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptchaAuthenticationFilter
 * Author:   HuangTaiHong
 * Date:     2018-04-04 上午 10:05
 * Description: 短信验证码校验过滤器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.authentication.mobile;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import roberto.growth.process.security.core.constant.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈短信验证码校验过滤器〉
 *
 * @author HuangTaiHong
 * @create 2018-04-04
 * @since 1.0.0
 */
@Getter
@Setter
public class SMSCaptchaAuthenticationFilter extends AbstractAuthenticationProcessingFilter{
    private boolean postOnly = true;
    private String mobileParameter = SecurityConstants.PARAMETER_NAME_SMS_CODE_REQUEST;

    public SMSCaptchaAuthenticationFilter(String filterUrl) {
        super(new AntPathRequestMatcher(filterUrl, "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,  HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !request.getMethod().equals("POST")) {
            throw new AuthenticationServiceException("不支持非POST方式的请求!" + request.getMethod());
        }

        String mobile = obtainMobile(request);

        if (mobile == null) {
            mobile = "";
        }

        mobile = mobile.trim();

        SMSCaptchaAuthenticationToken authRequest = new SMSCaptchaAuthenticationToken(mobile, null);

        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }


    protected String obtainMobile(HttpServletRequest request) {
        return request.getParameter(mobileParameter);
    }

    protected void setDetails(HttpServletRequest request, SMSCaptchaAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }
}