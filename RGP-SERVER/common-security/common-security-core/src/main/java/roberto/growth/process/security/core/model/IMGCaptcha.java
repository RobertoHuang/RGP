/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: IMGCaptcha
 * Author:   HuangTaiHong
 * Date:     2018-03-29 上午 11:13
 * Description: 图形验证码
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.model;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;

/**
 * 〈一句话功能简述〉<br>
 * 〈图形验证码〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29
 * @since 1.0.0
 */
@Getter
@Setter
public class IMGCaptcha extends BaseCaptcha {
    /**
     * 验证码
     **/
    private String code;

    /**
     * 验证码图片
     **/
    private BufferedImage image;

    public IMGCaptcha(String code, BufferedImage image, int expireIn) {
        super(expireIn);
        this.code = code;
        this.image = image;
    }
}