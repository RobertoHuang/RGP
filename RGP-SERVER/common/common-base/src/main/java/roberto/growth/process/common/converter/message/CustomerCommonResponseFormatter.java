/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: CustomerCommonResponseFormatter
 * Author:   HuangTaiHong
 * Date:     2018-02-23 下午 3:05
 * Description: JSON响应信息格式化工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.converter.message;

import com.alibaba.fastjson.JSONObject;
import roberto.growth.process.common.response.RGPGenericResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈JSON响应信息格式化工具类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-23
 * @since 1.0.0
 */
public class CustomerCommonResponseFormatter {
    /**
     * 功能描述: <br>
     * 〈正常请求响应消息格式化〉
     *
     * @param code
     * @param data
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/20 下午 6:56
     */
    public static String format(Integer code, String data) {
        return JSONObject.toJSONString(new RGPGenericResponse(code, data));
    }

    /**
     * 功能描述: <br>
     * 〈异常请求响应消息格式化〉
     *
     * @param path
     * @param code
     * @param exception
     * @param friendlyMsg
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/20 下午 7:00
     */
    public static String formatWithException(String path, Integer code, String exception, String friendlyMsg) {
        return JSONObject.toJSONString(new RGPGenericResponse(path, code, exception, friendlyMsg));
    }

    /**
     * 功能描述: <br>
     * 〈异常请求响应消息格式化〉
     *
     * @param path
     * @param code
     * @param exception
     * @param friendlyMsg
     * @param errorMessages
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/20 下午 7:02
     */
    public static String formatWithException(String path, Integer code, String exception, String friendlyMsg, Object errorMessages) {
        return JSONObject.toJSONString(new RGPGenericResponse(path, code, exception, friendlyMsg, errorMessages));
    }
}