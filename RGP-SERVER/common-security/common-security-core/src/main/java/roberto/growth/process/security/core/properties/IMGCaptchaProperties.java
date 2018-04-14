/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: IMGCaptchaProperties
 * Author:   HuangTaiHong
 * Date:     2018-03-28 下午 1:39
 * Description: 图片验证码属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈图片验证码属性〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28
 * @since 1.0.0
 */
@Getter
@Setter
public class IMGCaptchaProperties {
    /**
     * 图片边框
     **/
    private String border = "no";

    /**
     * 图片宽度
     **/
    private String imageWidth = "120";

    /**
     * 图片高度
     **/
    private String imageHeight = "35";

    /**
     * 验证码背景颜色渐变开始颜色
     **/
    private String backgroundClearFrom = "210,214,222";

    /**
     * 验证码背景颜色渐变结束颜色
     **/
    private String backgroundClearTo = "255,255,255";

    /**
     * 验证码的字符
     **/
    private String textProducerCharString = "0123456789";

    /**
     * 验证码字体颜色
     **/
    private String textProducerFontColor = "0,0,255";

    /**
     * 验证码干扰效果
     **/
    private String obscurificatorImpl = "com.google.code.kaptcha.impl.WaterRipple";

    /**
     * 验证码字体大小
     **/
    private String textProducerFontSize = "30";

    /**
     * 验证码文字长度
     **/
    private String textProducerCharLength = "4";

    /**
     * 验证码字体类型
     **/
    private String textProducerFontNames = "宋体,楷体,微软雅黑";

    /**
     * 验证码干扰颜色
     **/
    private String noiseColor = "red";

    /**
     * 验证码过期时间(单位s)
     **/
    private Integer expireIn = 60;

    /**
     * 需要验证码的请求
     **/
    private String filterURL;
}