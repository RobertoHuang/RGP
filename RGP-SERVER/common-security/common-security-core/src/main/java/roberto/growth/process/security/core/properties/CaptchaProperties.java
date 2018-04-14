/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CaptchaProperties
 * Author:   HuangTaiHong
 * Date:     2018-03-28 下午 2:07
 * Description: 验证码配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈验证码配置属性〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28 
 * @since 1.0.0
 */
@Setter
@Getter
public class CaptchaProperties {
    /**
     * 图片验证码
     **/
    private IMGCaptchaProperties IMGCaptcha = new IMGCaptchaProperties();

    /**
     * 短信验证码
     **/
    private SMSCaptchaProperties SMSCaptcha = new SMSCaptchaProperties();
}