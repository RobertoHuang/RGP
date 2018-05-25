/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserCenterFeignClient
 * Author:   HuangTaiHong
 * Date:     2018/5/23 0:00
 * Description: 用户中心Feign客户端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.feign;

import org.springframework.cloud.openfeign.FeignClient;
import roberto.growth.process.feign.fallback.UserCenterFeignClientFallBack;
import roberto.growth.process.uc.api.api.UserServiceApi;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户中心Feign客户端〉
 *
 * @author HuangTaiHong
 * @create 2018/5/23
 * @since 1.0.0
 */
@FeignClient(value = "USER-CENTER",fallback = UserCenterFeignClientFallBack.class)
public interface UserCenterFeignClient extends UserServiceApi {

}