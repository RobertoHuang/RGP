/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: AutoIncrementGenerator
 * Author:   HuangTaiHong
 * Date:     2018-02-26 下午 2:43
 * Description: ID自增生成器
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.generator.impl;

import roberto.growth.process.common.generator.IdentifierGenerator;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 〈一句话功能简述〉<br>
 * 〈ID自增生成器〉
 *
 * @author HuangTaiHong
 * @create 2018-02-26
 * @since 1.0.0
 */
public class AutoIncrementGenerator implements IdentifierGenerator {
    private Object mark;
    private long maxValue;
    private final Map<Object, AtomicLong> valueMap = new HashMap<>();

    public AutoIncrementGenerator(long maxValue) {
        this.mark = new Object();
        this.maxValue = maxValue;
    }

    @Override
    public Object generate() {
        return autoIncrement(mark);
    }

    private Long autoIncrement(Object mark) {
        AtomicLong value;
        synchronized (valueMap) {
            value = valueMap.get(mark);
            if (value == null) {
                value = new AtomicLong(0L);
                valueMap.put(mark, value);
            }
        }
        long autoIncrementResult = value.addAndGet(1L);
        // 当超过最大值后从0开始递增
        if (autoIncrementResult >= maxValue) {
            value.set(0L);
        }
        return Long.valueOf(autoIncrementResult);
    }
}