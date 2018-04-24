/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: SimpleResponse
 * Author:   HuangTaiHong
 * Date:     2018-03-20 下午 7:23
 * Description: 封装简单的响应信息
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 〈一句话功能简述〉<br>
 * 〈封装简单的响应信息〉
 *
 * @author HuangTaiHong
 * @create 2018-03-20
 * @since 1.0.0
 */
@Getter
@Setter
@AllArgsConstructor
public class SimpleResponse {
    private Object responseBody;
}