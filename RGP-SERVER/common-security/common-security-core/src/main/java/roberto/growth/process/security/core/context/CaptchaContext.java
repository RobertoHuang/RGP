/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CaptchaContext
 * Author:   HuangTaiHong
 * Date:     2018-03-29 上午 10:13
 * Description: 创建验证码上下文
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.context;

import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.strategy.CaptchaStrategy;

/**
 * 〈一句话功能简述〉<br> 
 * 〈创建验证码上下文〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29 
 * @since 1.0.0
 */
public class CaptchaContext {
    private CaptchaStrategy captchaStrategy;

    public CaptchaContext(CaptchaStrategy captchaStrategy) {
        this.captchaStrategy = captchaStrategy;
    }

    /**
     * 功能描述: <br>
     * 〈使用对应策略创建验证码〉
     *
     * @param request
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/29 上午 11:55
     */
    public void createCaptcha(ServletWebRequest request) throws Exception {
        this.captchaStrategy.createCaptcha(request);
    }
}