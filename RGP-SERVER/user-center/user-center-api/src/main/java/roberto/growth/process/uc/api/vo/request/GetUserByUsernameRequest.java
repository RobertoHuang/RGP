/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: GetUserByUsernameRequest
 * Author:   HuangTaiHong
 * Date:     2018/5/23 0:06
 * Description: 通过用户名获取用户信息请求
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
 * 〈通过用户名获取用户信息请求〉
 *
 * @author HuangTaiHong
 * @create 2018/5/23
 * @since 1.0.0
 */
@Getter
@Setter
public class GetUserByUsernameRequest extends RGPBaseRequest {
    private static final long serialVersionUID = 1003656917652498150L;

    /** 用户名 **/
    private String username;
}