/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CaptchaStrategyAdapt
 * Author:   HuangTaiHong
 * Date:     2018-03-29 下午 2:26
 * Description: 验证码生成策略适配器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.adapt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import roberto.growth.process.security.core.enums.RGPCaptchaGenerateTypeEnum;
import roberto.growth.process.security.core.strategy.CaptchaStrategy;
import roberto.growth.process.security.core.strategy.impl.IMGCaptchaStrategy;
import roberto.growth.process.security.core.strategy.impl.SMSCaptchaStrategy;

/**
 * 〈一句话功能简述〉<br> 
 * 〈验证码生成策略适配器〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29 
 * @since 1.0.0
 */
@Component
public class CaptchaStrategyAdapt {
    @Autowired
    private IMGCaptchaStrategy imgCaptchaStrategy;

    @Autowired
    private SMSCaptchaStrategy smsCaptchaStrategy;

    /**
     * 功能描述: <br>
     * 〈根据验证码类型适配对应生成策略〉
     *
     * @param captchaGenerateTypeEnum
     * @return:roberto.growth.process.security.core.strategy.CreateCaptchaStrategy
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/29 下午 2:15
     */
    public CaptchaStrategy adapt(RGPCaptchaGenerateTypeEnum captchaGenerateTypeEnum) {
        CaptchaStrategy captchaStrategy;
        switch (captchaGenerateTypeEnum){
            case IMG:
                captchaStrategy = imgCaptchaStrategy;
                break;
            case SMS:
                captchaStrategy = smsCaptchaStrategy;
                break;
            default:
                captchaStrategy = imgCaptchaStrategy;
                break;
        }
        return captchaStrategy;
    }
}