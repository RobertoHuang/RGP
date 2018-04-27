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

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import roberto.growth.process.uc.api.exception.RGPUserCenterException;
import roberto.growth.process.uc.api.vo.request.GetUserByPhoneNumberRequest;
import roberto.growth.process.uc.api.vo.request.SaveUserRequest;
import roberto.growth.process.uc.api.vo.request.ValidateUserRequest;
import roberto.growth.process.uc.api.vo.response.GetUserByPhoneNumberResponse;
import roberto.growth.process.uc.api.vo.response.SaveUserResponse;
import roberto.growth.process.uc.api.vo.response.ValidateUserResponse;

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
    @PostMapping(value = "/user/save")
    SaveUserResponse saveUser(SaveUserRequest saveUserRequest);

    /**
     * 功能描述: <br>
     * 〈校验用户信息〉
     *
     * @param validateUserRequest
     * @return roberto.growth.process.uc.api.vo.response.ValidateUserResponse
     * @throws RGPUserCenterException
     * @author HuangTaiHong
     * @date 2018.04.26 14:31:44
     */
    @PostMapping(value = "/user/validate")
    ValidateUserResponse validateUser(ValidateUserRequest validateUserRequest) throws RGPUserCenterException;

    /**
     * 功能描述: <br>
     * 〈通过手机号码查找用户信息〉
     *
     * @param getUserByPhoneNumberRequest
     * @return roberto.growth.process.uc.api.vo.response.GetUserByPhoneNumberResponse
     * @throws RGPUserCenterException
     * @author HuangTaiHong
     * @date 2018.04.26 14:40:47
     */
    @GetMapping(value = "/user/getByPhoneNumber")
    GetUserByPhoneNumberResponse getUserByPhoneNumber(GetUserByPhoneNumberRequest getUserByPhoneNumberRequest) throws RGPUserCenterException;
}