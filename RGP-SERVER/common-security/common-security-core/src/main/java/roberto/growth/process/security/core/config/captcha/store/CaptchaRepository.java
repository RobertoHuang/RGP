/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CaptchaRepository
 * Author:   HuangTaiHong
 * Date:     2018-05-15 上午 11:12
 * Description: 验证码存取器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config.captcha.store;

import org.springframework.web.context.request.ServletWebRequest;
import roberto.growth.process.security.core.enums.RGPCaptchaGenerateTypeEnum;
import roberto.growth.process.security.core.model.BaseCaptcha;

/**
 * 〈一句话功能简述〉<br>
 * 〈验证码存取器〉
 *
 * @author HuangTaiHong
 * @create 2018-05-15
 * @since 1.0.0
 */
public interface CaptchaRepository<C extends BaseCaptcha> {
    /**
     * 功能描述: <br>
     * 〈保存验证码〉
     *
     * @param request
     * @param captcha
     * @author HuangTaiHong
     * @date 2018.05.15 11:15:23
     */
    void save(ServletWebRequest request, C captcha);

    /**
     * 功能描述: <br>
     * 〈获取验证码〉
     *
     * @param request
     * @param type
     * @return roberto.growth.process.security.core.model.BaseCaptcha
     * @author HuangTaiHong
     * @date 2018.05.15 11:16:10
     */
    C get(ServletWebRequest request, RGPCaptchaGenerateTypeEnum type);

    /**
     * 功能描述: <br>
     * 〈移除验证码〉
     *
     * @param request
     * @param type
     * @author HuangTaiHong
     * @date 2018.05.15 11:16:23
     */
    void remove(ServletWebRequest request, RGPCaptchaGenerateTypeEnum type);
}