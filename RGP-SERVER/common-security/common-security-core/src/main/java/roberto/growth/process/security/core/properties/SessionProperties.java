/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SessionProperties
 * Author:   HuangTaiHong
 * Date:     2018-05-11 下午 7:26
 * Description: Session配置相关属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import roberto.growth.process.security.core.constant.SecurityConstants;

/**
 * 〈一句话功能简述〉<br> 
 * 〈Session配置相关属性〉
 *
 * @author HuangTaiHong
 * @create 2018-05-11 
 * @since 1.0.0
 */
@Getter
@Setter
public class SessionProperties {

    /** 同一个用户在系统中的最大session数 默认1 **/
    private int maximumSessions = 1;

    /** 达到最大session时是否阻止新的登录请求，默认为false，不阻止，新的登录会将老的登录失效掉 **/
    private boolean maxSessionsPreventsLogin = false;

    /** session失效时跳转的地址 **/
    private String sessionInvalidUrl = SecurityConstants.DEFAULT_SESSION_INVALID_URL;
}