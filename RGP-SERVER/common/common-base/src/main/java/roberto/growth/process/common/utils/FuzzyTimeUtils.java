/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: FuzzyTimeUtils
 * Author:   HuangTaiHong
 * Date:     2018-02-26 下午 3:49
 * Description: 时间编码工具类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.utils;

import org.apache.commons.lang.StringUtils;

import java.util.Calendar;

/**
 * 〈一句话功能简述〉<br>
 * 〈时间编码工具类〉
 *
 * @author HuangTaiHong
 * @create 2018-02-26
 * @since 1.0.0
 */
public class FuzzyTimeUtils {
    private static final int HOUR_LENGTH = 5;
    private static final int MINUTE_LENGTH = 6;

    /**
     * 功能描述: <br>
     * 〈返回当前时间混淆串 日期+小时+分钟〉
     *
     * @param
     * @return:java.lang.String
     * @since: 1.0.0
     * @Author:HuangTaiHong
     * @Date: 2018/2/26 下午 4:06
     */
    public static String FuzzyNowDate() {
        Calendar current = Calendar.getInstance();
        int currentDate = current.get(Calendar.DAY_OF_YEAR);
        int currentHour = current.get(Calendar.HOUR_OF_DAY);
        int currentMinute = current.get(Calendar.MINUTE);

        currentDate <<= HOUR_LENGTH + MINUTE_LENGTH;
        currentHour <<= MINUTE_LENGTH;

        int currentStamp = currentDate + currentHour + currentMinute;
        String currentStampToHEX = Integer.toHexString(currentStamp);
        return StringUtils.leftPad(currentStampToHEX, 5, "0");
    }
}