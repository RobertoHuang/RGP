/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: AbstractSessionStrategy
 * Author:   HuangTaiHong
 * Date:     2018-05-11 下午 7:18
 * Description: Session策略抽象类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.config.session.strategy;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import roberto.growth.process.common.response.SimpleResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br>
 * 〈Session策略抽象类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-11
 * @since 1.0.0
 */
@Slf4j
public abstract class AbstractSessionStrategy {
    /** 跳转的URL **/
    private String destinationUrl;

    /** 跳转前是否创建新的session **/
    private boolean createNewSession = true;

    /** 重定向策略 使用默认重定向策略 **/
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (createNewSession) {
            request.getSession();
        }
        String accept = request.getHeader("Accept");
        if (!ContentType.APPLICATION_JSON.getMimeType().equals(accept)) {
            log.info("session失效,跳转到" + destinationUrl);
            redirectStrategy.sendRedirect(request, response, destinationUrl);
        } else {
            String message = "session已失效";
            if (isConcurrency()) {
                message = message + "，有可能是并发登录导致的";
            }

            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(JSONObject.toJSONString(new SimpleResponse(message)));
        }
    }

    /** Session失效是否是并发导致的 **/
    protected boolean isConcurrency() {
        return false;
    }

    public void setCreateNewSession(boolean createNewSession) {
        this.createNewSession = createNewSession;
    }
}