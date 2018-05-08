/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: BrowserSecurityController
 * Author:   HuangTaiHong
 * Date:     2018-02-22 下午 4:55
 * Description: 认证失败处理请求
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import roberto.growth.process.common.response.SimpleResponse;
import roberto.growth.process.common.utils.ObjectUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈认证失败处理请求〉
 *
 * @author HuangTaiHong
 * @create 2018-02-22
 * @since 1.0.0
 */
@Slf4j
@Controller
public class BrowserSecurityController {
    private RequestCache requestCache = new HttpSessionRequestCache();

    @ResponseBody
    @RequestMapping(value = "/authentication/toLogin")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (ObjectUtils.isNotEmpty(savedRequest)) {
            log.info("引发跳转的请求是:" + savedRequest.getRedirectUrl());
        }
        return new SimpleResponse("访问的服务需要身份认证");
    }

    @RequestMapping(value = "/authentication/toLogin", produces = {"text/html"})
    public String requireAuthenticationHtml(HttpServletRequest request, HttpServletResponse response) {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (ObjectUtils.isNotEmpty(savedRequest)) {
            log.info("引发跳转的请求是:" + savedRequest.getRedirectUrl());
        }
        return "UserLogin";
    }
}