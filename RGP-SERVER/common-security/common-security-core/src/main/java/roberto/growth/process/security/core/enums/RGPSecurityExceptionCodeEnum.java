/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPSecurityExceptionCodeEnum
 * Author:   HuangTaiHong
 * Date:     2018-03-28 下午 2:45
 * Description: 异常码枚举类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.enums;

import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 * 〈异常码枚举类〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28
 * @since 1.0.0
 */
@Getter
public enum RGPSecurityExceptionCodeEnum {
    CAPTCHA_GENERATE_STRATEGY_NOT_FOUND("E00001", "验证码生成策略未找到"), CREATE_CAPTCHA_ERROR("E00002", "创建验证码失败"),
    SEND_SMS_CAPTCHA_ERROR("E00003", "发送短信验证码失败");

    private final String code;
    private final String value;

    RGPSecurityExceptionCodeEnum(String code, String label) {
        this.code = code;
        this.value = label;
    }

    public static RGPSecurityExceptionCodeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        if (values() == null) {
            return null;
        }

        for (RGPSecurityExceptionCodeEnum t : values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }
}