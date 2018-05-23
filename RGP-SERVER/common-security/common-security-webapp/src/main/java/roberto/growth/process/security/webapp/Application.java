/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: Application
 * Author:   HuangTaiHong
 * Date:     2018-02-11 下午 5:28
 * Description: 启动类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.security.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 〈一句话功能简述〉<br> 
 * 〈启动类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-11 
 * @since 1.0.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "roberto.growth.process")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}