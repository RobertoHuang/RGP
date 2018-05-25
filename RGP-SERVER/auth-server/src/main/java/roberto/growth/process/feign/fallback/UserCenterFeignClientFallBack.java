/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: UserCenterFeignClientFallBack
 * Author:   HuangTaiHong
 * Date:     2018/5/25 22:39
 * Description: UserCenterFeignClient降级
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.feign.fallback;

import org.springframework.stereotype.Component;
import roberto.growth.process.feign.UserCenterFeignClient;
import roberto.growth.process.uc.api.exception.RGPUserCenterException;
import roberto.growth.process.uc.api.vo.request.GetUserByPhoneNumberRequest;
import roberto.growth.process.uc.api.vo.request.SaveUserRequest;
import roberto.growth.process.uc.api.vo.request.ValidateUserRequest;
import roberto.growth.process.uc.api.vo.response.GetUserByPhoneNumberResponse;
import roberto.growth.process.uc.api.vo.response.GetUserByUsernameResponse;
import roberto.growth.process.uc.api.vo.response.SaveUserResponse;
import roberto.growth.process.uc.api.vo.response.ValidateUserResponse;

/**
 * 〈一句话功能简述〉<br> 
 * 〈UserCenterFeignClient降级〉
 *
 * @author HuangTaiHong
 * @create 2018/5/25
 * @since 1.0.0
 */
@Component
public class UserCenterFeignClientFallBack implements UserCenterFeignClient {
    @Override
    public SaveUserResponse saveUser(SaveUserRequest saveUserRequest) {
        return null;
    }

    @Override
    public ValidateUserResponse validateUser(ValidateUserRequest validateUserRequest) throws RGPUserCenterException {
        return null;
    }

    @Override
    public GetUserByPhoneNumberResponse getUserByPhoneNumber(GetUserByPhoneNumberRequest getUserByPhoneNumberRequest) throws RGPUserCenterException {
        return null;
    }

    @Override
    public GetUserByUsernameResponse getUserByUsername(String username) {
        return null;
    }
}