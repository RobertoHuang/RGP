/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: AuthServiceFeignClient
 * Author:   HuangTaiHong
 * Date:     2018/5/22 0:26
 * Description: 用户认证
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.acd.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户认证〉
 *
 * @author HuangTaiHong
 * @create 2018/5/22
 * @since 1.0.0
 */
@FeignClient(value = "auth-service")
public interface AuthServiceFeignClient {
    @PostMapping(value = "/oauth/token")
    String getToken(@RequestHeader(value = "Authorization") String authorization, @RequestParam("grant_type") String type, @RequestParam("username") String username, @RequestParam("password") String password);
}