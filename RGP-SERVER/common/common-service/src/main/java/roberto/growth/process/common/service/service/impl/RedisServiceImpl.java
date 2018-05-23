/**
 * Copyright (C), 2015-2018, ND Co., Ltd.
 * FileName: RedisServiceImpl
 * Author:   HuangTaiHong
 * Date:     2018-04-13 下午 4:22
 * Description: Redis Service层实现类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package roberto.growth.process.common.service.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import reactor.core.publisher.Flux;
import roberto.growth.process.common.service.service.RedisService;

import java.time.Duration;
import java.util.*;

/**
 * 〈一句话功能简述〉<br>
 * 〈Redis Service层实现类〉
 *
 * @author HuangTaiHong
 * @create 2018-04-13
 * @since 1.0.0
 */
@Service("redisService")
public class RedisServiceImpl<T> implements RedisService<T> {
    @Autowired(required = false)
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @Override
    public void deleteCacheObject(String key) {
        reactiveRedisTemplate.delete(key).block();
    }

    @Override
    public void deleteCacheCollection(Collection collection) {
        reactiveRedisTemplate.delete(collection).block();
    }

    @Override
    public <T> T getCacheObject(String key) {
        ReactiveValueOperations<String, T> operations = reactiveRedisTemplate.opsForValue();
        return operations.get(key).block();
    }

    @Override
    public <T> Boolean setCacheObject(String key, T value) {
        ReactiveValueOperations<String, T> operations = reactiveRedisTemplate.opsForValue();
        return operations.set(key, value).block();
    }

    @Override
    public <T> Boolean setCacheObject(String key, T value, Duration timeout) {
        ReactiveValueOperations<String, T> operations = reactiveRedisTemplate.opsForValue();
        return operations.set(key, value, timeout).block();
    }

    @Override
    public <T> List<T> getCacheList(String key) {
        List<T> dataList = new ArrayList<>();
        ReactiveListOperations<String, T> operations = reactiveRedisTemplate.opsForList();
        for (int i = 0; i < operations.size(key).block(); i++) {
            dataList.add(operations.index(key, i).block());
        }
        return dataList;
    }

    @Override
    public <T> Boolean setCacheList(String key, List<T> dataList) {
        ReactiveListOperations<String, T> operations = reactiveRedisTemplate.opsForList();
        if (!ObjectUtils.isEmpty(dataList)) {
            T[] tArray = (T[]) dataList.toArray();
            Long block = operations.leftPushAll(key, tArray).block();
            return block == dataList.size();
        } else {
            return false;
        }
    }

    @Override
    public <T> Set<T> getCacheSet(String key) {
        Set<T> dataSet = new HashSet<>();
        ReactiveSetOperations<String, T> operations = reactiveRedisTemplate.opsForSet();
        for (int i = 0; i < operations.size(key).block(); i++) {
            dataSet.add(operations.pop(key).block());
        }
        return dataSet;
    }

    @Override
    public <T> Boolean setCacheSet(String key, Set<T> dataSet) {
        ReactiveSetOperations<String, T> operations = reactiveRedisTemplate.opsForSet();
        if (!ObjectUtils.isEmpty(dataSet)) {
            T[] tArray = (T[]) dataSet.toArray();
            Long block = operations.add(key, tArray).block();
            return block == dataSet.size();
        } else {
            return false;
        }
    }

    @Override
    public <T> Map<String, T> getCacheMap(String key) {
        Map<String, T> dataMap = new HashMap<>();
        ReactiveHashOperations<String, String, T> operations = reactiveRedisTemplate.opsForHash();
        Flux<Map.Entry<String, T>> entries = operations.entries(key);
        if (!ObjectUtils.isEmpty(entries)) {
            List<Map.Entry<String, T>> entryList = entries.collectList().block();
            for (Map.Entry<String, T> entry : entryList) {
                dataMap.put(entry.getKey(), entry.getValue());
            }
        }
        return dataMap;
    }

    @Override
    public <T> Boolean setCacheMap(String key, Map<String, T> dataMap) {
        ReactiveHashOperations<String, String, T> operations = reactiveRedisTemplate.opsForHash();
        if (!ObjectUtils.isEmpty(dataMap)) {
            return operations.putAll(key, dataMap).block();
        } else {
            return false;
        }
    }

    @Override
    public <T> Map<Integer, T> getCacheIntegerMap(String key) {
        Map<Integer, T> dataMap = new HashMap<>();
        ReactiveHashOperations<String, Integer, T> operations = reactiveRedisTemplate.opsForHash();
        Flux<Map.Entry<Integer, T>> entries = operations.entries(key);
        if (!ObjectUtils.isEmpty(entries)) {
            List<Map.Entry<Integer, T>> entryList = entries.collectList().block();
            for (Map.Entry<Integer, T> entry : entryList) {
                dataMap.put(entry.getKey(), entry.getValue());
            }
        }
        return dataMap;
    }

    @Override
    public <T> Boolean setCacheIntegerMap(String key, Map<Integer, T> dataMap) {
        ReactiveHashOperations<String, Integer, T> operations = reactiveRedisTemplate.opsForHash();
        if (!ObjectUtils.isEmpty(dataMap)) {
            return operations.putAll(key, dataMap).block();
        } else {
            return false;
        }
    }
}