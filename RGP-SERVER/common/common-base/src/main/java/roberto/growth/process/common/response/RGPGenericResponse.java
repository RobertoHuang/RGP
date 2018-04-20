/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RGPGenericResponse
 * Author:   HuangTaiHong
 * Date:     2018-03-20 下午 6:49
 * Description: RGP系统通用响应
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

/**
 * 〈一句话功能简述〉<br>
 * 〈RGP系统通用响应〉
 *
 * @author HuangTaiHong
 * @create 2018-03-20
 * @since 1.0.0
 */
@Getter
@Setter
public class RGPGenericResponse {
    private Meta meta;
    private Object data;

    public RGPGenericResponse(Integer code, Object data) {
        this.data = data;
        this.meta = new Meta(code);
    }

    public RGPGenericResponse(String path, Integer code, String exception, String friendlyMsg, Object errorMessages) {
        this.meta = new Meta(path, code, exception, friendlyMsg, errorMessages);
    }

    @Getter
    @Setter
    class Meta {
        /**
         * 请求路径
         **/
        @JSONField
        private String path;

        /**
         * 请求结果状态码
         **/
        @JSONField(ordinal = 1)
        private Integer retCode;

        /**
         * 友善的错误提示
         **/
        @JSONField(ordinal = 2)
        private String friendlyMsg;

        /**
         * 异常类型
         **/
        @JSONField(ordinal = 3)
        private String exception;

        /**
         * 具体的异常消息描述列表
         **/
        @JSONField(ordinal = 4)
        private Object errorMessages;

        /**
         * 前端界面的异常拦截器，是否要做特殊处理
         **/
        @JSONField(ordinal = 5)
        private boolean isFilterExcluded = false;

        /**
         * 当前请求时间戳信息
         **/
        @JSONField(ordinal = 6)
        private final Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        public Meta(Integer retCode) {
            this.retCode = retCode;
        }

        public Meta(String path, Integer retCode, String exception, String friendlyMsg, Object errorMessages) {
            this.path = path;
            this.retCode = retCode;
            this.exception = exception;
            this.friendlyMsg = friendlyMsg;
            this.errorMessages = errorMessages;
        }
    }
}