/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SocialProperties
 * Author:   HuangTaiHong
 * Date:     2018-05-08 上午 10:54
 * Description: SpringSocial配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈SpringSocial配置属性〉
 *
 * @author HuangTaiHong
 * @create 2018-05-08 
 * @since 1.0.0
 */
@Getter
@Setter
public class SocialProperties {
    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();

    private WeiXinProperties weixin = new WeiXinProperties();
}