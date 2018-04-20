/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserCenterFeignClient
 * Author:   HuangTaiHong
 * Date:     2018-04-18 下午 6:46
 * Description: 用户中心Feign客户端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.ua.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import roberto.growth.process.uc.api.api.UserServiceApi;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户中心Feign客户端〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18 
 * @since 1.0.0
 */
@FeignClient(name = "USER-CENTER")
public interface UserCenterFeignClient extends UserServiceApi{

}