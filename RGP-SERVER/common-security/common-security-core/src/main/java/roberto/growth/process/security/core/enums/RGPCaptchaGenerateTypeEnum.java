/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPCaptchaGenerateTypeEnum
 * Author:   HuangTaiHong
 * Date:     2018-03-28 下午 3:46
 * Description: 验证码生成方式枚举类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.enums;

import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 * 〈验证码生成方式枚举类〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28
 * @since 1.0.0
 */
@Getter
public enum RGPCaptchaGenerateTypeEnum {
    SMS("SMS", "短信验证码"), IMG("IMG", "图形验证码");

    private final String code;
    private final String value;

    RGPCaptchaGenerateTypeEnum(String code, String label) {
        this.code = code;
        this.value = label;
    }

    public static RGPCaptchaGenerateTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        if (values() == null) {
            return null;
        }

        for (RGPCaptchaGenerateTypeEnum t : values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }
}