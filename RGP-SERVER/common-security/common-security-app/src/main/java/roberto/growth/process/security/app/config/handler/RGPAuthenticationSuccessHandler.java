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
package roberto.growth.process.security.app.config.handler;

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
 * 〈校验成功处理器〉
 *
 * @author HuangTaiHong
 * @create 2018-05-14 
 * @since 1.0.0
 */
public class RGPAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
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