/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: IMGCaptchaStrategy
 * Author:   HuangTaiHong
 * Date:     2018-03-29 上午 10:11
 * Description: 图形验证码策略
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.strategy.impl;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.config.captcha.store.CaptchaRepository;
import roberto.growth.process.security.core.constant.SecurityConstants;
import roberto.growth.process.security.core.enums.RGPCaptchaGenerateTypeEnum;
import roberto.growth.process.security.core.exception.ValidateCaptchaException;
import roberto.growth.process.security.core.model.IMGCaptcha;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * 〈一句话功能简述〉<br>
 * 〈图形验证码策略〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29
 * @since 1.0.0
 */
@Component
public class IMGCaptchaStrategy extends ABSCaptchaStrategy<IMGCaptcha> {
    @Autowired
    private DefaultKaptcha captchaProducer;

    @Autowired
    private CaptchaRepository<IMGCaptcha> captchaRepository;

    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    private final String PARAMNAME_ON_VALIDATE = SecurityConstants.DEFAULT_PARAMETER_NAME_CODE_IMG;

    @Override
    protected IMGCaptcha generateCaptcha(ServletWebRequest request) {
        // 生成验证码
        String captcha = captchaProducer.createText();
        // 创建验证码图片
        BufferedImage bufferedImage = captchaProducer.createImage(captcha);
        // 返回图形验证码实体
        return new IMGCaptcha(captcha, bufferedImage, customerSecurityProperties.getCaptcha().getImg().getExpireIn());
    }

    @Override
    protected void sendCaptcha(ServletWebRequest request, IMGCaptcha captcha) throws Exception {
        ImageIO.write(captcha.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

    @Override
    public void validateCaptcha(ServletWebRequest request) {
        // 获取验证码
        IMGCaptcha captcha = captchaRepository.get(request, RGPCaptchaGenerateTypeEnum.IMG);
        if (ObjectUtils.isEmpty(captcha)) {
            throw new ValidateCaptchaException("验证码不存在");
        } else if (captcha.isCaptchaExpried()) {
            throw new ValidateCaptchaException("验证码已过期");
        } else {
            // 使用具体策略校验验证码
            String codeInRequest = request.getRequest().getParameter(PARAMNAME_ON_VALIDATE);
            if (StringUtils.isBlank(codeInRequest)) {
                throw new ValidateCaptchaException("验证码的值不能为空");
            } else if (!StringUtils.equals(captcha.getCode(), codeInRequest)) {
                throw new ValidateCaptchaException("输入的验证码不正确");
            }
            captchaRepository.remove(request, RGPCaptchaGenerateTypeEnum.IMG);
        }
    }
}