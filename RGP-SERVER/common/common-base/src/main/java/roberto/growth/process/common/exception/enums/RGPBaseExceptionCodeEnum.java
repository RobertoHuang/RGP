/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPBaseExceptionCodeEnum
 * Author:   HuangTaiHong
 * Date:     2018-04-03 下午 2:35
 * Description: RGP通用异常码枚举类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.exception.enums;

import lombok.Getter;

/**
 * 〈一句话功能简述〉<br>
 * 〈RGP通用异常码枚举类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-03
 * @since 1.0.0
 */
@Getter
public enum RGPBaseExceptionCodeEnum {
    DATABASE_CONNECTION_ERROR("E00001", "数据库连接失败"), GET_LOCAL_IPADDRESS_ERROR("E00002", "获取本地IP地址失败");

    private final String code;
    private final String value;

    RGPBaseExceptionCodeEnum(String code, String label) {
        this.code = code;
        this.value = label;
    }

    public static RGPBaseExceptionCodeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }

        if (values() == null) {
            return null;
        }

        for (RGPBaseExceptionCodeEnum t : values()) {
            if (t.getCode().equals(code)) {
                return t;
            }
        }
        return null;
    }
}