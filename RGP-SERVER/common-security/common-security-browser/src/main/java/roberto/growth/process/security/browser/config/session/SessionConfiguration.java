/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SessionConfiguration
 * Author:   HuangTaiHong
 * Date:     2018/5/12 10:01
 * Description: Session管理配置
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.browser.config.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.session.InvalidSessionStrategy;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;
import roberto.growth.process.security.browser.config.session.strategy.RGPExpiredSessionStrategy;
import roberto.growth.process.security.browser.config.session.strategy.RGPInvalidSessionStrategy;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;


/**
 * 〈一句话功能简述〉<br>
 * 〈Session管理配置〉
 *
 * @author HuangTaiHong
 * @create 2018/5/12
 * @since 1.0.0
 */
@Component
// @EnableRedisHttpSession
// Spring Session + Redis参考https://blog.csdn.net/b376924098/article/details/79701631
public class SessionConfiguration {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Bean
    @ConditionalOnMissingBean(InvalidSessionStrategy.class)
    public InvalidSessionStrategy invalidSessionStrategy() {
        return new RGPInvalidSessionStrategy(customerSecurityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }

    @Bean
    @ConditionalOnMissingBean(SessionInformationExpiredStrategy.class)
    public SessionInformationExpiredStrategy sessionInformationExpiredStrategy() {
        return new RGPExpiredSessionStrategy(customerSecurityProperties.getBrowser().getSession().getSessionInvalidUrl());
    }
}