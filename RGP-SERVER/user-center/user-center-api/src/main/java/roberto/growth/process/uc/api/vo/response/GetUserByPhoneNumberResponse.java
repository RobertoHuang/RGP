/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: GetUserByPhoneNumberResponse
 * Author:   HuangTaiHong
 * Date:     2018-04-26 下午 2:29
 * Description: 通过手机号码查找用户信息响应
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.api.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import roberto.growth.process.common.vo.response.RGPBaseResponse;
import roberto.growth.process.uc.api.vo.domain.UserDetail;

/**
 * 〈一句话功能简述〉<br> 
 * 〈通过手机号码查找用户信息响应〉
 *
 * @author HuangTaiHong
 * @create 2018-04-26 
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class GetUserByPhoneNumberResponse extends RGPBaseResponse{
    private static final long serialVersionUID = -5897638618968018656L;

    private UserDetail userDetail;
}