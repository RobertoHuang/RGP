/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPExpiredSessionStrategy
 * Author:   HuangTaiHong
 * Date:     2018-05-11 下午 7:22
 * Description: Session过期策略
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.config.session.strategy;

import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Session过期策略〉
 *
 * @author HuangTaiHong
 * @create 2018-05-11 
 * @since 1.0.0
 */
public class RGPExpiredSessionStrategy extends AbstractSessionStrategy implements SessionInformationExpiredStrategy {
    public RGPExpiredSessionStrategy(String invalidSessionUrl) {
        super(invalidSessionUrl);
    }

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException, ServletException {
        onSessionInvalid(event.getRequest(), event.getResponse());
    }

    @Override
    protected boolean isConcurrency() {
        return true;
    }
}