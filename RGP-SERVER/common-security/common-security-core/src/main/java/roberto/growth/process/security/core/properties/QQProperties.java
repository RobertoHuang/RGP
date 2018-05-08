/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQProperties
 * Author:   HuangTaiHong
 * Date:     2018-05-08 上午 10:54
 * Description: QQ登录配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈QQ登录配置属性〉
 *
 * @author HuangTaiHong
 * @create 2018-05-08
 * @since 1.0.0
 */
@Getter
@Setter
public class QQProperties {
    private String appId;

    private String appSecret;

    private String providerId = "qq";
}