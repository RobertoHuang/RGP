/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CustomerAuthenticationSuccessHandler
 * Author:   HuangTaiHong
 * Date:     2018-03-28 上午 9:28
 * Description: 登录成功处理
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.handler;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.entity.ContentType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈登录成功处理〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28
 * @since 1.0.0
 */
public class CustomerAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        String accept = request.getHeader("Accept");
        if (ContentType.APPLICATION_JSON.getMimeType().equals(accept)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(authentication));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}