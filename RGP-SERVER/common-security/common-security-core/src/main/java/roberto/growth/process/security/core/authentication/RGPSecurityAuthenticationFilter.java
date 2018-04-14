/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPSecurityAuthenticationFilter
 * Author:   HuangTaiHong
 * Date:     2018-04-04 下午 3:18
 * Description: RGP Security验证过滤器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.authentication;

import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletRequestAttributes;
import roberto.growth.process.security.core.constant.SecurityConstants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈RGP Security验证过滤器〉
 *
 * @author HuangTaiHong
 * @create 2018-04-04
 * @since 1.0.0
 */
@Setter
public class RGPSecurityAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private boolean postOnly = true;

    /**
     * SESSION操作工具类
     **/
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !RequestMethod.POST.equals(request.getMethod())) {
            throw new AuthenticationServiceException("不支持非POST方式的请求!" + request.getMethod());
        } else {
            RGPSecurityAuthenticationToken authenticationToken = new RGPSecurityAuthenticationToken(obtainUsername(request), obtainPassword(request));
            // 回填手机号
            sessionStrategy.setAttribute(new ServletRequestAttributes(request), "SPRING_SECURITY_LOGIN_MOBILE", obtainMobile(request));
            // 回填用户名
            sessionStrategy.setAttribute(new ServletRequestAttributes(request), "SPRING_SECURITY_LOGIN_USERNAME", obtainUsername(request));

            // 设置手机号
            authenticationToken.setMobile(obtainMobile(request));
            // 设置短信验证码
            authenticationToken.setSMSCode(obtainSMSCode(request));
            // 允许子类设置详细属性
            setDetails(request, authenticationToken);

            return getAuthenticationManager().authenticate(authenticationToken);
        }
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return StringUtils.trimToEmpty(request.getParameter(getUsernameParameter()));
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return StringUtils.trimToEmpty(request.getParameter(getPasswordParameter()));
    }

    protected String obtainMobile(HttpServletRequest request) {
        return StringUtils.trimToEmpty(request.getParameter(SecurityConstants.PARAMETER_NAME_SMS_CODE_REQUEST));
    }

    protected String obtainSMSCode(HttpServletRequest request) {
        return StringUtils.trimToEmpty(request.getParameter(SecurityConstants.PARAMETER_NAME_SMS_CODE_VALIDATE));
    }
}