/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: BaseCaptcha
 * Author:   HuangTaiHong
 * Date:     2018-03-29 上午 11:01
 * Description: 验证码基类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 〈一句话功能简述〉<br>
 * 〈验证码基类〉
 *
 * @author HuangTaiHong
 * @create 2018-03-29
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
public class BaseCaptcha implements Serializable{
    private static final long serialVersionUID = -4342392387582118978L;

    /** 验证码 **/
    private String code;

    /** 验证码过期时间 **/
    private LocalDateTime expireTime;

    /** expireIn 单位(s) **/
    public BaseCaptcha(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    /** expireIn 过期时间 **/
    public BaseCaptcha(String code, LocalDateTime expireIn) {
        this.code = code;
        this.expireTime = expireIn;
    }

    /**
     * 功能描述: <br>
     * 〈判断验证码是否过期〉
     *
     * @param
     * @return:boolean
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/3/29 上午 11:15
     */
    public boolean isCaptchaExpried() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}