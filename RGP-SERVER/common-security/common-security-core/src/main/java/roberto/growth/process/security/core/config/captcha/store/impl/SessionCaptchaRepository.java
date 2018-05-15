/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SessionCaptchaRepository
 * Author:   HuangTaiHong
 * Date:     2018-05-15 上午 11:13
 * Description: 基于Session的验证码存取器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config.captcha.store.impl;

import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.config.captcha.store.CaptchaRepository;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.enums.RGPCaptchaGenerateTypeEnum;
import roberto.growth.process.security.core.model.BaseCaptcha;

/**
 * 〈一句话功能简述〉<br>
 * 〈基于Session的验证码存取器〉
 *
 * @author HuangTaiHong
 * @create 2018-05-15
 * @since 1.0.0
 */
public class SessionCaptchaRepository<C extends BaseCaptcha> implements CaptchaRepository<C> {
    /** 操作session的工具类 **/
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /** 验证码放入session时的前缀 **/
    private final String SESSION_KEY_PREFIX = SecurityConstants.CAPTCHA_SESSION_KEY_PREFIX;

    @Override
    public void save(ServletWebRequest request, C captcha) {
        sessionStrategy.setAttribute(request, getSessionKey(captcha.getRgpCaptchaGenerateTypeEnum()), captcha);
    }

    @Override
    public C get(ServletWebRequest request, RGPCaptchaGenerateTypeEnum type) {
        return (C) sessionStrategy.getAttribute(request, getSessionKey(type));
    }

    @Override
    public void remove(ServletWebRequest request, RGPCaptchaGenerateTypeEnum type) {
        sessionStrategy.removeAttribute(request, getSessionKey(type));
    }

    private String getSessionKey(RGPCaptchaGenerateTypeEnum type) {
        return SESSION_KEY_PREFIX + type.getCode();
    }
}