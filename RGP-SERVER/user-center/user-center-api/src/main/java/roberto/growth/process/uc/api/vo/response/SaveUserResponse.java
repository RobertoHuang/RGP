/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SaveUserResponse
 * Author:   HuangTaiHong
 * Date:     2018-04-16 下午 5:34
 * Description: 保存用户响应
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
 * 〈保存用户响应〉
 *
 * @author HuangTaiHong
 * @create 2018-04-16 
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class SaveUserResponse extends RGPBaseResponse{
    private static final long serialVersionUID = 5385243191761636892L;

    private UserDetail userDetail;
}