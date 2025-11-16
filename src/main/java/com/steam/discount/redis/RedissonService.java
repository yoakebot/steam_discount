package com.steam.discount.redis;

import jakarta.annotation.Resource;
import org.redisson.api.RBucket;
import org.redisson.api.RList;
import org.redisson.api.RSet;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class RedissonService {

    @Resource
    private RedissonClient redissonClient;


    public <T> void save(String key, T object, Duration duration) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        bucket.set(object, duration);
    }

    public <T> T get(String key) {
        RBucket<T> bucket = redissonClient.getBucket(key);
        return bucket.get();
    }

    public <T> void saveList(String key, List<T> data) {
        RList<T> rList = redissonClient.getList(key);
        rList.clear(); // 清空旧数据（可选）
        rList.addAll(data);
    }

    public <T> void saveListExpire(String key, List<T> data, Duration duration) {
        RList<T> rList = redissonClient.getList(key);
        rList.clear(); // 清空旧数据（可选）
        rList.addAll(data);
        rList.expire(duration);
    }

    public <T> List<T> getList(String key) {
        RList<T> rList = redissonClient.getList(key);
        return new ArrayList<>(rList);
    }

    public <T> void saveSet(String key, Set<T> data) {
        RSet<T> rList = redissonClient.getSet(key);
        rList.clear(); // 清空旧数据（可选）
        rList.addAll(data);
    }

    public <T> void saveSetExpire(String key, Set<T> data, Duration duration) {
        RSet<T> rList = redissonClient.getSet(key);
        rList.addAll(data);
        rList.expire(duration);
    }

    public <T> Set<T> getSet(String key) {
        RSet<T> rList = redissonClient.getSet(key);
        return new TreeSet<>(rList);
    }

    public boolean deleteKey(String key) {
        return redissonClient.getKeys().delete(key) > 0;
    }
}
