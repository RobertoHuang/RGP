/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPLogoutSuccessHandler
 * Author:   HuangTaiHong
 * Date:     2018/5/13 1:25
 * Description: 退出登录成功处理器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.config.logout.handler;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import roberto.growth.process.common.response.SimpleResponse;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈退出登录成功处理器〉
 *
 * @author HuangTaiHong
 * @create 2018/5/13
 * @since 1.0.0
 */
@Slf4j
public class RGPLogoutSuccessHandler implements LogoutSuccessHandler {
    private String signOutSuccessUrl;

    public RGPLogoutSuccessHandler(String signOutSuccessUrl) {
        this.signOutSuccessUrl = signOutSuccessUrl;
    }

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("退出成功");
        if (StringUtils.isBlank(signOutSuccessUrl)) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(new SimpleResponse("退出登录成功")));
        } else {
            response.sendRedirect(signOutSuccessUrl);
        }
    }
}