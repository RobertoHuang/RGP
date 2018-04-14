/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CaptchaController
 * Author:   HuangTaiHong
 * Date:     2018-03-28 下午 1:34
 * Description: 验证码Controller层
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.adapt.CaptchaStrategyAdapt;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.context.CaptchaContext;
import roberto.growth.process.security.core.enums.RGPCaptchaGenerateTypeEnum;
import roberto.growth.process.security.core.enums.RGPSecurityExceptionCodeEnum;
import roberto.growth.process.security.core.exception.RGPSecurityException;
import roberto.growth.process.security.core.strategy.CaptchaStrategy;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈验证码Controller层〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28
 * @since 1.0.0
 */
@RestController
public class CaptchaController {
    @Autowired
    private CaptchaStrategyAdapt captchaStrategyAdapt;

    @GetMapping(SecurityConstants.GENERATE_CAPTCHA_URL_PREFIX + "/{type}")
    public void createCaptcha(@PathVariable String type, HttpServletRequest request, HttpServletResponse response) throws RGPSecurityException {
        try {
            RGPCaptchaGenerateTypeEnum captchaGenerateType = RGPCaptchaGenerateTypeEnum.getByCode(type);
            if (ObjectUtils.isEmpty(captchaGenerateType)) {
                throw new RGPSecurityException(RGPSecurityExceptionCodeEnum.CAPTCHA_GENERATE_STRATEGY_NOT_FOUND);
            } else {
                CaptchaStrategy strategy = captchaStrategyAdapt.adapt(captchaGenerateType);
                new CaptchaContext(strategy).createCaptcha(new ServletWebRequest(request, response));
            }
        } catch (Exception e) {
            throw new RGPSecurityException(RGPSecurityExceptionCodeEnum.CREATE_CAPTCHA_ERROR, e);
        }
    }
}