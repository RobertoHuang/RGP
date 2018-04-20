/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ObjectUtils
 * Author:   HuangTaiHong
 * Date:     2018-04-18 下午 12:02
 * Description: Object工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.utils;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br>
 * 〈Object工具类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18
 * @since 1.0.0
 */
public class ObjectUtils {
    public static boolean isEmpty(Object object) {
        if (object == null) {
            return true;
        } else if (object instanceof String) {
            return object == null
                    || object.toString().trim().length() == 0
                    || "NULL".equalsIgnoreCase(object.toString());
        } else {
            if (object instanceof Collection) {
                if (((Collection) object).isEmpty()) {
                    return true;
                }
            } else if (object.getClass().isArray()) {
                if (Array.getLength(object) == 0) {
                    return true;
                }
            } else {
                if (!(object instanceof Map)) {
                    return false;
                }

                if (((Map) object).isEmpty()) {
                    return true;
                }
            }
            return false;
        }
    }

    public static boolean isNotEmpty(Object object) {
        return !isEmpty(object);
    }
}