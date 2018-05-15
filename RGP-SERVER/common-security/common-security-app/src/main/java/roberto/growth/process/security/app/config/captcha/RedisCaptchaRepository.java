/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RedisCaptchaRepository
 * Author:   HuangTaiHong
 * Date:     2018-05-15 上午 11:13
 * Description: 基于Redis的验证码存取器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.app.config.captcha;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.common.service.service.RedisService;
import roberto.growth.process.common.utils.ObjectUtils;
import roberto.growth.process.security.core.config.captcha.store.CaptchaRepository;
import roberto.growth.process.security.core.enums.RGPCaptchaGenerateTypeEnum;
import roberto.growth.process.security.core.enums.RGPSecurityExceptionCodeEnum;
import roberto.growth.process.security.core.exception.RGPSecurityRTException;
import roberto.growth.process.security.core.model.BaseCaptcha;

import java.time.Duration;

/**
 * 〈一句话功能简述〉<br>
 * 〈基于Redis的验证码存取器〉
 *
 * @author HuangTaiHong
 * @create 2018-05-15
 * @since 1.0.0
 */
@Component
public class RedisCaptchaRepository<C extends BaseCaptcha> implements CaptchaRepository<C> {
    @Autowired
    private RedisService<C> redisService;

    @Override
    public void save(ServletWebRequest request, C captcha) {
        redisService.setCacheObject(buildKey(request, captcha.getRgpCaptchaGenerateTypeEnum()), captcha, Duration.ofMinutes(30));
    }

    @Override
    public C get(ServletWebRequest request, RGPCaptchaGenerateTypeEnum type) {
        C value = redisService.getCacheObject(buildKey(request, type));
        return ObjectUtils.isEmpty(value) ? null : value;
    }

    @Override
    public void remove(ServletWebRequest request, RGPCaptchaGenerateTypeEnum type) {
        redisService.deleteCacheObject(buildKey(request, type));
    }

    /**
     * 功能描述: <br>
     * 〈构建验证码KEY〉
     *
     * @param request
     * @param type
     * @return java.lang.String
     * @author HuangTaiHong
     * @date 2018.05.15 11:26:04
     */
    private String buildKey(ServletWebRequest request, RGPCaptchaGenerateTypeEnum type) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new RGPSecurityRTException(RGPSecurityExceptionCodeEnum.DEVICEID_NOT_EXIST);
        }
        return "code:" + type.getCode().toLowerCase() + ":" + deviceId;
    }
}