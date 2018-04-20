/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: JwtAuthenticationRequest
 * Author:   HuangTaiHong
 * Date:     2018-04-18 上午 11:15
 * Description: 获取Token请求
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.ua.api.vo.request;

import lombok.Getter;
import lombok.Setter;
import roberto.growth.process.common.vo.request.RGPBaseRequest;

/**
 * 〈一句话功能简述〉<br> 
 * 〈获取Token请求〉
 *
 * @author HuangTaiHong
 * @create 2018-04-18 
 * @since 1.0.0
 */
@Getter
@Setter
public class JwtAuthenticationRequest extends RGPBaseRequest{
    private static final long serialVersionUID = -4376097967161532378L;

    private String username;

    private String password;
}