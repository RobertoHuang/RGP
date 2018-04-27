/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CustomerAuthenticationFailureHandler
 * Author:   HuangTaiHong
 * Date:     2018-03-28 上午 9:48
 * Description: 登录失败处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.handler;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import roberto.growth.process.common.response.SimpleResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈登录失败处理〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28
 * @since 1.0.0
 */
public class CustomerAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String accept = request.getHeader("Accept");
        if (ContentType.APPLICATION_JSON.getMimeType().equals(accept)) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(new SimpleResponse(exception.getMessage())));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}