/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: GetUserByPhoneNumberRequest
 * Author:   HuangTaiHong
 * Date:     2018-04-26 下午 2:28
 * Description: 通过手机号码查找用户信息请求
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
 * 〈通过手机号码查找用户信息请求〉
 *
 * @author HuangTaiHong
 * @create 2018-04-26 
 * @since 1.0.0
 */
@Getter
@Setter
public class GetUserByPhoneNumberRequest extends RGPBaseRequest {
    private static final long serialVersionUID = 1003656917652498150L;

    private String phoneNumber;
}