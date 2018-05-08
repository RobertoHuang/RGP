/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: QQConnectionFactory
 * Author:   HuangTaiHong
 * Date:     2018-05-08 上午 10:47
 * Description: 连接服务提供商工厂类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.core.social.qq.connet;

import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import roberto.growth.process.security.core.social.qq.api.QQ;

/**
 * 〈一句话功能简述〉<br> 
 * 〈连接服务提供商工厂类〉
 *
 * @author HuangTaiHong
 * @create 2018-05-08 
 * @since 1.0.0
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}