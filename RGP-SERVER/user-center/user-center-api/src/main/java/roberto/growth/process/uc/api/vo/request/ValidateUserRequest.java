/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: ValidateUserRequest
 * Author:   HuangTaiHong
 * Date:     2018-04-18 上午 11:25
 * Description: 校验用户信息请求
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.uc.api.vo.request;

import lombok.Getter;
import lombok.Setter;
import roberto.growth.process.common.vo.request.RGPBaseRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈校验用户信息请求〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18 
 * @since 1.0.0
 */
@Getter
@Setter
public class ValidateUserRequest extends RGPBaseRequest{
    private static final long serialVersionUID = 4635873808233419410L;

    private String username;

    private String password;
}