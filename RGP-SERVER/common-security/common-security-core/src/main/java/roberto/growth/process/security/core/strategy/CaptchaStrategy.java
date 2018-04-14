/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CaptchaStrategy
 * Author:   HuangTaiHong
 * Date:     2018-03-29 上午 10:07
 * Description: 验证码策略接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.strategy;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈验证码策略接口〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29 
 * @since 1.0.0
 */
public interface CaptchaStrategy {
    /**
     * 功能描述: <br>
     * 〈创建验证码〉
     *
     * @param request
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/3 上午 11:54
     */
    void createCaptcha(ServletWebRequest request) throws Exception;

    /**
     * 功能描述: <br>
     * 〈校验验证码〉
     *
     * @param request
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/29 下午 5:52
     */
    void validateCaptcha(ServletWebRequest request);
}