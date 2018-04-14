/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: IExceptionWrapper
 * Author:   HuangTaiHong
 * Date:     2018-02-26 上午 11:16
 * Description: 异常包装接口
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.exception.base;

import roberto.growth.process.common.exception.model.ExceptionDetailsInfo;

/**
 * 〈一句话功能简述〉<br>
 * 〈异常包装接口〉
 *
 * @author HuangTaiHong
 * @create 2018-02-26
 * @since 1.0.0
 */
public interface IExceptionWrapper {
    /**
     * 功能描述: <br>
     * 〈获取异常码〉
     *
     * @param
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/26 上午 11:16
     */
    String getCode();

    /**
     * 功能描述: <br>
     * 〈获取异常发生时间〉
     *
     * @param
     * @return:java.lang.Long
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/26 上午 11:24
     */
    Long getTimestamp();

    /**
     * 功能描述: <br>
     * 〈获取异常详细信息〉
     *
     * @param
     * @return:roberto.growth.process.common.exception.model.ExceptionDetailsInfo
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/26 下午 12:00
     */
    ExceptionDetailsInfo getExceptionDetailsInfo();
}