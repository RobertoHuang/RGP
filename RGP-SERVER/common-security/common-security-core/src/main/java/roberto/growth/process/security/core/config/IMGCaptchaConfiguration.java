/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: IMGCaptchaConfiguration
 * Author:   HuangTaiHong
 * Date:     2018-03-28 下午 1:35
 * Description: 图形验证码配置类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.config;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import roberto.growth.process.security.core.properties.CustomerSecurityProperties;
import roberto.growth.process.security.core.properties.IMGCaptchaProperties;

import java.util.Properties;

/**
 * 〈一句话功能简述〉<br>
 * 〈图形验证码配置类〉
 *
 * @author HuangTaiHong
 * @create 2018-03-28
 * @since 1.0.0
 */
@Configuration
public class IMGCaptchaConfiguration {
    @Autowired
    private CustomerSecurityProperties customerSecurityProperties;

    @Bean
    public DefaultKaptcha captchaProducer() {
        // 获取图形验证码配置属性
        IMGCaptchaProperties IMGCaptchaProperties = customerSecurityProperties.getCaptcha().getIMGCaptcha();

        // 设置图形验证码配置属性
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty(Constants.KAPTCHA_BORDER, IMGCaptchaProperties.getBorder());
        // 图片宽度
        properties.setProperty(Constants.KAPTCHA_IMAGE_WIDTH, IMGCaptchaProperties.getImageWidth());
        // 图片高度
        properties.setProperty(Constants.KAPTCHA_IMAGE_HEIGHT, IMGCaptchaProperties.getImageHeight());
        // 验证码背景颜色渐变开始颜色
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_FROM, IMGCaptchaProperties.getBackgroundClearFrom());
        // 验证码背景颜色渐变结束颜色
        properties.setProperty(Constants.KAPTCHA_BACKGROUND_CLR_TO, IMGCaptchaProperties.getBackgroundClearTo());
        // 验证码可选字符
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_STRING, IMGCaptchaProperties.getTextProducerCharString());
        // 验证码字体颜色
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_COLOR, IMGCaptchaProperties.getTextProducerFontColor());
        // 验证码干扰效果
        properties.setProperty(Constants.KAPTCHA_OBSCURIFICATOR_IMPL, IMGCaptchaProperties.getObscurificatorImpl());
        // 验证码字体大小
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_SIZE, IMGCaptchaProperties.getTextProducerFontSize());
        // 验证码文字长度
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_CHAR_LENGTH, IMGCaptchaProperties.getTextProducerCharLength());
        // 验证码字体类型
        properties.setProperty(Constants.KAPTCHA_TEXTPRODUCER_FONT_NAMES, IMGCaptchaProperties.getTextProducerFontNames());
        // 验证码干扰颜色
        properties.setProperty(Constants.KAPTCHA_NOISE_COLOR, IMGCaptchaProperties.getNoiseColor());

        DefaultKaptcha captchaProducer = new DefaultKaptcha();
        captchaProducer.setConfig(new Config(properties));
        return captchaProducer;
    }
}