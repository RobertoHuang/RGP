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
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.exception.ValidateCaptchaException;
import roberto.growth.process.security.core.model.SMSCaptcha;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;
import roberto.growth.process.security.core.utils.SendSMSCaptchaUtils;

/**
 * 〈一句话功能简述〉<br> 
 * 〈手机短信验证码策略〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29 
 * @since 1.0.0
 */
@Component
public class SMSCaptchaStrategy extends ABSCaptchaStrategy<SMSCaptcha>{
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    private final String SESSION_KEY = SecurityConstants.CAPTCHA_SESSION_KEY_PREFIX + "SMS";
    private final String PARAMNAME_ON_REQUEST = SecurityConstants.PARAMETER_NAME_SMS_CODE_REQUEST;
    private final String PARAMNAME_ON_VALIDATE = SecurityConstants.PARAMETER_NAME_SMS_CODE_VALIDATE;

    @Override
    protected SMSCaptcha generateCaptcha() {
        String code = RandomStringUtils.randomNumeric(customerSecurityProperties.getCaptcha().getSMSCaptcha().getLength());
        return new SMSCaptcha(code,customerSecurityProperties.getCaptcha().getSMSCaptcha().getExpireIn());
    }

    @Override
    protected String getSessionKey() {
        return SESSION_KEY;
    }

    @Override
    protected void sendCaptcha(ServletWebRequest request, SMSCaptcha captcha) throws Exception {
        String phoneNumber = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), PARAMNAME_ON_REQUEST);
        SendSMSCaptchaUtils.sendSMSCaptcha(phoneNumber, captcha.getCode());
    }

    @Override
    protected void validateCaptchaInStrategy(ServletWebRequest request, SMSCaptcha captcha) {
        try {
            String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), PARAMNAME_ON_VALIDATE);
            if (StringUtils.isBlank(codeInRequest)) {
                throw new ValidateCaptchaException("验证码的值不能为空");
            } else if (!StringUtils.equals(captcha.getCode(), codeInRequest)) {
                throw new ValidateCaptchaException("输入的验证码不正确");
            }
        } catch (ServletRequestBindingException e) {
            throw new ValidateCaptchaException("获取验证码的值失败", e);
        }
    }
}