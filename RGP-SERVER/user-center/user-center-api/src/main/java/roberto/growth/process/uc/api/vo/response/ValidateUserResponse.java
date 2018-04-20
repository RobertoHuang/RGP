/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ValidateUserResponse
 * Author:   HuangTaiHong
 * Date:     2018-04-18 上午 11:26
 * Description: 校验用户信息响应
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.api.vo.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import roberto.growth.process.common.vo.response.RGPBaseResponse;
import roberto.growth.process.uc.api.vo.domain.UserDetail;

/**
 * 〈一句话功能简述〉<br> 
 * 〈校验用户信息响应〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18 
 * @since 1.0.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateUserResponse extends RGPBaseResponse{
    private static final long serialVersionUID = -1133477961414551934L;

    private UserDetail userDetail;
}