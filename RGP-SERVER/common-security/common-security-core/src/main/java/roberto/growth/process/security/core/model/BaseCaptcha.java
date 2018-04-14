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
import lombok.Setter;

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
public class BaseCaptcha {
    /**
     * 验证码过期时间
     **/
    private LocalDateTime expireTime;

    /**
     * expireIn 单位(s)
     **/
    public BaseCaptcha(int expireIn) {
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
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