/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQ
 * Author:   HuangTaiHong
 * Date:     2018-05-04 下午 5:18
 * Description: QQ API接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.qq.api;

/**
 * 〈一句话功能简述〉<br> 
 * 〈QQ API接口〉
 *
 * @author HuangTaiHong
 * @create 2018-05-04 
 * @since 1.0.0
 */
public interface QQ {
    /**
     * 功能描述: <br>
     * 〈获取QQ互联返回的用户信息〉
     *
     * @return roberto.growth.process.security.core.social.qq.api.QQUserInfo
     * @author HuangTaiHong
     * @date 2018.05.04 17:38:22
     */
    QQUserInfo getUserInfo();
}