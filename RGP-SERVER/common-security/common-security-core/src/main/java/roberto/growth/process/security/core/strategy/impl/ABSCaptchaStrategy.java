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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.config.captcha.store.CaptchaRepository;
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
    @Autowired
    private CaptchaRepository<C> captchaRepository;

    @Override
    public void createCaptcha(ServletWebRequest request) throws Exception {
        // 创建验证码
        C captcha = this.generateCaptcha(request);
        // 保存验证码
        captchaRepository.save(request, captcha);
        // 发送验证码
        this.sendCaptcha(request, captcha);
    }

    /**
     * 功能描述: <br>
     * 〈生成图形验证码〉
     *
     * @param request
     * @return C
     * @author HuangTaiHong
     * @date 2018.04.26 11:59:08
     */
    protected abstract C generateCaptcha(ServletWebRequest request);

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
}