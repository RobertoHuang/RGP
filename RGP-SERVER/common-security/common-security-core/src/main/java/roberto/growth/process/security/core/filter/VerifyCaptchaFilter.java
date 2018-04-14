/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: VerifyCaptchaFilter
 * Author:   HuangTaiHong
 * Date:     2018-03-29 下午 5:17
 * Description: 校验验证码过滤器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.filter;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;
import roberto.growth.process.security.core.adapt.CaptchaStrategyAdapt;
import roberto.growth.process.security.core.enums.RGPCaptchaGenerateTypeEnum;
import roberto.growth.process.security.core.exception.ValidateCaptchaException;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;
import roberto.growth.process.security.core.strategy.CaptchaStrategy;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 〈一句话功能简述〉<br>
 * 〈校验验证码过滤器〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29
 * @since 1.0.0
 */
@Component("verifyCaptchaFilter")
public class VerifyCaptchaFilter extends OncePerRequestFilter implements InitializingBean {
    @Autowired
    private CaptchaStrategyAdapt captchaStrategyAdapt;

    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Resource(name = "customerAuthenticationFailureHandler")
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 需要验证码的请求
     **/
    private Map<String, RGPCaptchaGenerateTypeEnum> needCaptchaRequestMap = new HashMap<>();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CaptchaStrategy captchaStrategy = getCaptchaStrategy(request);
        if (!ObjectUtils.isEmpty(captchaStrategy)) {
            try {
                captchaStrategy.validateCaptcha(new ServletWebRequest(request, response));
            } catch (ValidateCaptchaException e) {
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 功能描述: <br>
     * 〈获取验证码生成策略〉
     *
     * @param request
     * @return:roberto.growth.process.security.core.strategy.CaptchaStrategy
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/2 下午 2:41
     */
    private CaptchaStrategy getCaptchaStrategy(HttpServletRequest request) {
        // GET请求不需要验证码
         if (!StringUtils.equalsIgnoreCase(RequestMethod.GET.name(), request.getMethod())) {
            Set<String> needCaptchaURLSet = needCaptchaRequestMap.keySet();
            for (String needCaptchaURL : needCaptchaURLSet) {
                if (new AntPathMatcher().match(needCaptchaURL, request.getRequestURI())) {
                    RGPCaptchaGenerateTypeEnum rgpCaptchaGenerateTypeEnum = needCaptchaRequestMap.get(needCaptchaURL);
                    return captchaStrategyAdapt.adapt(rgpCaptchaGenerateTypeEnum);
                }
            }
        }
        return null;
    }

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        // 登录处理页
        needCaptchaRequestMap.put(customerSecurityProperties.getBrowser().getLoginProcessingUrl(), RGPCaptchaGenerateTypeEnum.IMG);
        // 用户自定义配置需要验证码的URL
        String imgFilterURL = customerSecurityProperties.getCaptcha().getIMGCaptcha().getFilterURL();
        if (!ObjectUtils.isEmpty(imgFilterURL)) {
            needCaptchaRequestMap.put(imgFilterURL, RGPCaptchaGenerateTypeEnum.IMG);
        }
    }
}