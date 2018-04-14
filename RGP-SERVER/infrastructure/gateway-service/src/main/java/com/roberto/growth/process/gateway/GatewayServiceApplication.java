/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: GatewayServiceApplication
 * Author:   HuangTaiHong
 * Date:     2018-04-11 上午 10:10
 * Description: 网关启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.roberto.growth.process.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 〈一句话功能简述〉<br>
 * 〈网关启动类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-11
 * @since 1.0.0
 */
@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class GatewayServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayServiceApplication.class, args);
    }
}