/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserCenterProperties
 * Author:   HuangTaiHong
 * Date:     2018-04-26 下午 2:12
 * Description: 用户中心配置属性
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户中心配置属性〉
 *
 * @author HuangTaiHong
 * @create 2018-04-26 
 * @since 1.0.0
 */
@Getter
@Setter
public class UserCenterProperties {
    /** 用户中心地址 **/
    private String url = "http://localhost:8883";
}