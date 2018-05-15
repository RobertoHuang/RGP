/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SMSCaptchaStrategy
 * Author:   HuangTaiHong
 * Date:     2018-03-29 上午 10:09
 * Description: 手机短信验证码策略
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.strategy.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.config.captcha.store.CaptchaRepository;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.enums.RGPCaptchaGenerateTypeEnum;
import roberto.growth.process.security.core.exception.ValidateCaptchaException;
import roberto.growth.process.security.core.model.SMSCaptcha;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

/**
 * 〈一句话功能简述〉<br>
 * 〈手机短信验证码策略〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29
 * @since 1.0.0
 */
@Component
public class SMSCaptchaStrategy extends ABSCaptchaStrategy<SMSCaptcha> {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Autowired
    private CaptchaRepository<SMSCaptcha> captchaRepository;

    private final String SESSION_KEY = SecurityConstants.CAPTCHA_SESSION_KEY_PREFIX + "SMS";
    private final String PARAMNAME_ON_REQUEST = SecurityConstants.PARAMETER_NAME_SMS_CODE_REQUEST;
    private final String PARAMNAME_ON_VALIDATE = SecurityConstants.PARAMETER_NAME_SMS_CODE_VALIDATE;

    @Override
    protected SMSCaptcha generateCaptcha(ServletWebRequest request) {
        String phoneNumber = request.getRequest().getParameter(PARAMNAME_ON_REQUEST);
        String captchaCode = RandomStringUtils.randomNumeric(customerSecurityProperties.getCaptcha().getSms().getLength());
        return new SMSCaptcha(phoneNumber, captchaCode, customerSecurityProperties.getCaptcha().getSms().getExpireIn());
    }

    @Override
    protected void sendCaptcha(ServletWebRequest request, SMSCaptcha captcha) throws Exception {
        String phoneNumber = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), PARAMNAME_ON_REQUEST);
        // SendSMSCaptchaUtils.sendSMSCaptcha(phoneNumber, captcha.getCode());
    }

    @Override
    public void validateCaptcha(ServletWebRequest request) {
        // 获取验证码
        SMSCaptcha captcha = captchaRepository.get(request, RGPCaptchaGenerateTypeEnum.SMS);
        if (ObjectUtils.isEmpty(captcha)) {
            throw new ValidateCaptchaException("验证码不存在");
        } else if (captcha.isCaptchaExpried()) {
            throw new ValidateCaptchaException("验证码已过期");
        } else {
            String codeInRequest = request.getRequest().getParameter(PARAMNAME_ON_VALIDATE);
            String mobileInRequest = request.getRequest().getParameter(PARAMNAME_ON_REQUEST);
            if (StringUtils.isBlank(codeInRequest)) {
                throw new ValidateCaptchaException("验证码的值不能为空");
            } else if (!StringUtils.equals(captcha.getMobile(), mobileInRequest) || !StringUtils.equals(captcha.getCode(), codeInRequest)) {
                throw new ValidateCaptchaException("输入的验证码不正确");
            }
            captchaRepository.remove(request, RGPCaptchaGenerateTypeEnum.SMS);
        }
    }
}