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
package roberto.growth.process.security.browser.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
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

    /** 重定向策略 **/
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    /** 跳转前是否创建新的session **/
    private boolean createNewSession = true;

    private ObjectMapper objectMapper = new ObjectMapper();

    public AbstractSessionStrategy(String invalidSessionUrl) {
        Assert.isTrue(UrlUtils.isValidRedirectUrl(invalidSessionUrl), "url must start with '/' or with 'http(s)'");
        this.destinationUrl = invalidSessionUrl;
    }

    protected void onSessionInvalid(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (createNewSession) {
            request.getSession();
        }

        String targetUrl;
        String sourceUrl = request.getRequestURI();
        if (StringUtils.endsWithIgnoreCase(sourceUrl, ".html")) {
            targetUrl = destinationUrl+".html";
            log.info("session失效,跳转到"+targetUrl);
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }else{
            String message = "session已失效";
            if(isConcurrency()){
                message = message + "，有可能是并发登录导致的";
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(message)));
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