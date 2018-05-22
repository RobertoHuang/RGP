/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: AuthClientDemoApplication
 * Author:   HuangTaiHong
 * Date:     2018/5/21 22:50
 * Description: 用户校验客户端启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.acd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户校验客户端启动类〉
 *
 * @author HuangTaiHong
 * @create 2018/5/21
 * @since 1.0.0
 */
@EnableEurekaClient
@EnableFeignClients
@SpringBootApplication
public class AuthClientDemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthClientDemoApplication.class, args);
    }
}