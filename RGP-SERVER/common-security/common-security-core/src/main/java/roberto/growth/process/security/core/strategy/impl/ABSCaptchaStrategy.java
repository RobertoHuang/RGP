/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ABSCaptchaStrategy
 * Author:   HuangTaiHong
 * Date:     2018-03-29 上午 10:50
 * Description: 抽象验证码生成策略类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.strategy.impl;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.exception.ValidateCaptchaException;
import roberto.growth.process.security.core.model.BaseCaptcha;
import roberto.growth.process.security.core.strategy.CaptchaStrategy;

/**
 * 〈一句话功能简述〉<br>
 * 〈抽象验证码生成策略类〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29
 * @since 1.0.0
 */
public abstract class ABSCaptchaStrategy<C extends BaseCaptcha> implements CaptchaStrategy {
    /**
     * SESSION操作工具类
     **/
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    @Override
    public void createCaptcha(ServletWebRequest request) throws Exception {
        // 创建验证码
        C captcha = this.generateCaptcha();
        // 将验证码保存到SESSION中
        sessionStrategy.setAttribute(request, this.getSessionKey(), captcha);
        // 发送验证码
        this.sendCaptcha(request, captcha);
    }

    @Override
    public void validateCaptcha(ServletWebRequest request) {
        String sessionKey = this.getSessionKey();
        // 从Session中获取验证码
        C codeInSession = (C) sessionStrategy.getAttribute(request, sessionKey);
        if (ObjectUtils.isEmpty(codeInSession)) {
            throw new ValidateCaptchaException("验证码不存在");
        } else if (codeInSession.isCaptchaExpried()) {
            throw new ValidateCaptchaException("验证码已过期");
        } else {
            // 使用具体策略校验验证码
            this.validateCaptchaInStrategy(request, codeInSession);
            sessionStrategy.removeAttribute(request, sessionKey);
        }
    }

    /**
     * 功能描述: <br>
     * 〈生成图形验证码〉
     *
     * @param
     * @return:C
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/29 上午 11:30
     */
    protected abstract C generateCaptcha();

    /**
     * 功能描述: <br>
     * 〈获取验证码在SESSION中保存的KEY〉
     *
     * @param
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/29 下午 7:26
     */
    protected abstract String getSessionKey();

    /**
     * 功能描述: <br>
     * 〈发送验证码〉
     *
     * @param request
     * @param captcha
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/3 上午 11:51
     */
    protected abstract void sendCaptcha(ServletWebRequest request, C captcha) throws Exception;

    /**
     * 功能描述: <br>
     * 〈校验验证码〉
     *
     * @param request
     * @param captcha
     * @return:void
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/29 下午 7:32
     */
    protected abstract void validateCaptchaInStrategy(ServletWebRequest request, C captcha);
}