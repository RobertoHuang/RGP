/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: AuthServiceFeignClientFallBack
 * Author:   HuangTaiHong
 * Date:     2018/5/25 22:07
 * Description: AuthServiceFeignClient降级
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.acd.feign;

/**
 * 〈一句话功能简述〉<br> 
 * 〈AuthServiceFeignClient降级〉
 *
 * @author HuangTaiHong
 * @create 2018/5/25
 * @since 1.0.0
 */
public class AuthServiceFeignClientFallBack implements AuthServiceFeignClient{
    @Override
    public String getToken(String type, String username, String password) {
        return "xxx";
    }
}