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

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import roberto.growth.process.common.response.SimpleResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈认证失败处理请求〉
 *
 * @author HuangTaiHong
 * @create 2018-02-22
 * @since 1.0.0
 */
@Controller
public class BrowserSecurityController {
    @ResponseBody
    @RequestMapping(value = "/authentication/toLogin")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication() {
        return new SimpleResponse("访问的服务需要身份认证");
    }

    @RequestMapping(value = "/authentication/toLogin", produces = {"text/html"})
    public String requireAuthenticationHtml() {
        return "UserLogin";
    }
}