/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPInvalidSessionStrategy
 * Author:   HuangTaiHong
 * Date:     2018-05-11 下午 7:23
 * Description: Session无效策略
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.config.session.strategy;

import org.springframework.security.web.session.InvalidSessionStrategy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Session无效策略〉
 *
 * @author HuangTaiHong
 * @create 2018-05-11 
 * @since 1.0.0
 */
public class RGPInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {
    public RGPInvalidSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        onSessionInvalid(request, response);
    }
}