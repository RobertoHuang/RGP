/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SaveUserRequest
 * Author:   HuangTaiHong
 * Date:     2018-04-16 下午 5:13
 * Description: 保存用户请求
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
 * 〈保存用户请求〉
 *
 * @author HuangTaiHong
 * @create 2018-04-16 
 * @since 1.0.0
 */
@Getter
@Setter
public class SaveUserRequest extends RGPBaseRequest{
    private static final long serialVersionUID = -637713454554285000L;

    private String username;

    private String passwrod;

    private String phoneNumber;

    private String emailAddress;
}