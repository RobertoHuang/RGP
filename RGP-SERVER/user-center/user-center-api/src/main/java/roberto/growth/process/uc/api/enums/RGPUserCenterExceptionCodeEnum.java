/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPUserCenterExceptionCodeEnum
 * Author:   HuangTaiHong
 * Date:     2018-04-18 下午 1:52
 * Description: RGP用户中心异常枚举类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.api.enums;

import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 * 〈RGP用户中心异常枚举类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18
 * @since 1.0.0
 */
@Getter
public enum RGPUserCenterExceptionCodeEnum {
    USERNAME_NOT_FOUNT("E00001", "用户名不存在"), USERNAME_PASSWORD_NOT_MATCH("E00002", "用户名与密码不匹配");

    private final String code;
    private final String value;

    RGPUserCenterExceptionCodeEnum(String code, String label) {
        this.code = code;
        this.value = label;
    }

    public static RGPUserCenterExceptionCodeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        if (values() == null) {
            return null;
        }

        for (RGPUserCenterExceptionCodeEnum t : values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }
}