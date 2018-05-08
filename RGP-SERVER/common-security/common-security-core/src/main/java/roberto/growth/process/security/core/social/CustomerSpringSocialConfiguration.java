/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CustomerSpringSocialConfiguration
 * Author:   HuangTaiHong
 * Date:     2018-05-08 上午 11:05
 * Description: 个性化SocialAuthenticationFilter配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social;

import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 〈一句话功能简述〉<br>
 * 〈个性化SocialAuthenticationFilter配置类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-08
 * @since 1.0.0
 */
public class CustomerSpringSocialConfiguration extends SpringSocialConfigurer {
    private String filterProcessesUrl;

    public CustomerSpringSocialConfiguration(String filterProcessesUrl) {
        this.filterProcessesUrl = filterProcessesUrl;
    }

    @Override
    protected <T> T postProcess(T object) {
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        filter.setSignupUrl("/register");
        return (T) filter;
    }
}