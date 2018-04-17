/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserServiceApi
 * Author:   HuangTaiHong
 * Date:     2018-04-16 下午 5:12
 * Description: 用户服务API
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.api.api;

import org.springframework.web.bind.annotation.PostMapping;
import roberto.growth.process.uc.api.vo.request.SaveUserRequest;
import roberto.growth.process.uc.api.vo.response.SaveUserResponse;

/**
 * 〈一句话功能简述〉<br> 
 * 〈用户服务API〉
 *
 * @author HuangTaiHong
 * @create 2018-04-16
 * @since 1.0.0
 */
public interface UserServiceApi {
    /**
     * 功能描述: <br>
     * 〈保存用户〉
     *
     * @param saveUserRequest
     * @return:roberto.growth.process.uc.api.vo.response.SaveUserResponse
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/4/16 下午 6:51
     */
    @PostMapping(value = "/save")
    SaveUserResponse saveUser(SaveUserRequest saveUserRequest);
}