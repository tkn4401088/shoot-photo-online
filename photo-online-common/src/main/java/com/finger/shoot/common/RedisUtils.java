package com.finger.shoot.common;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by zb 2017/6/6.  redis工具类
 */
@Component
public class RedisUtils<T> {

    private static final Long defaultLiveTime = 24 * 60 * 60l; //默认缓存1天
    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Byte[]> redisTemplate;

    @Resource(name = "transactionRedisTemplate")
    private RedisTemplate<String, String> transactionRedisTemplate;

    /**
     * 保存到redis
     *
     * @param key
     * @param value
     * @param liveTime
     * @return
     */
    public boolean setEx(final String key, final Object value, final Long liveTime) {
        return redisTemplate.execute(
                new RedisCallback<Boolean>() {
                    public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                        connection.setEx(
                                serializable(key),
                                (null == liveTime || 0l == liveTime ? defaultLiveTime : liveTime),
                                serializable(value));
                        return true;
                    }
                });
    }

    /**
     * 序列化处理
     * @param obj
     * @return
     */
    public static byte[] serializable(Object obj) {
        if (obj == null) {
            return null;
        }
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            // 序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 反序列化处理
     * @param bytes
     * @return
     */
    public static Object unserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        ByteArrayInputStream bais = null;
        try {
            // 反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 从redis获取
     *
     * @param key
     * @return
     */
    public Object get(final String key) {
        return redisTemplate.execute(
                new RedisCallback<Object>() {
                    public Object doInRedis(RedisConnection connection) throws DataAccessException {
                        return unserialize(connection.get(serializable( key )));
                    }
                }
        );
    }

    public Long increment(String key) {
        return transactionRedisTemplate.opsForValue().increment(key, 1);
    }

    /**
     * 从redis获取String
     *
     * @param key
     * @return
     */
    public String getString(final String key) {
        return redisTemplate.execute(
                new RedisCallback<String>() {
                    public String doInRedis(RedisConnection connection) throws DataAccessException {
                        Object value =
                                SerializationUtils.deserialize(connection.get(SerializationUtils.serialize(key)));

                        return (null == value ? null : (String) value);
                    }
                });
    }

    /**
     * 删除key
     *
     * @param key
     * @return
     */
    public Long del(final String key) {
        return redisTemplate.execute(
                new RedisCallback<Long>() {
                    public Long doInRedis(RedisConnection connection) throws DataAccessException {
                        return connection.del(SerializationUtils.serialize(key));
                    }
                });
    }

    /**
     * 检查redis中是否存在对应数据
     *
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 插入K, V数据到Redis中
     *
     * @param key
     * @param map
     * @return
     */
    public void setHSet(String key, Map<String, T> map) {
        HashOperations<String, String, T> ops = transactionRedisTemplate.opsForHash();
        ops.putAll(key, map);
    }

    /**
     * 获取所有hash数据
     *
     * @param key
     * @return
     */
    public Map<String, T> getHEnties(String key) {
        HashOperations<String, String, T> ops = transactionRedisTemplate.opsForHash();
        return ops.entries(key);
    }

    /**
     * 检查Hash中是否存在特定的key
     *
     * @param key
     * @param hashKey
     * @return
     */
    public Boolean hasHashKey(String key, String hashKey) {
        return transactionRedisTemplate.opsForHash().hasKey(key, hashKey);
    }

    /**
     * 插入K，V数据到Redis中，并设置生存时间
     *
     * @param key
     * @param map
     * @param liveTime
     */
    public void setHSet(String key, Map<String, T> map, Long liveTime) {
        setHSet(key, map);
        transactionRedisTemplate.expire(key, liveTime, TimeUnit.SECONDS);
    }

    /**
     * 获取Map类型的值
     *
     * @param key
     * @param hsetKey
     * @return
     */
    public T getHSetValue(String key, String hsetKey) {
        HashOperations<String, String, T> ops = transactionRedisTemplate.opsForHash();
        return ops.get(key, hsetKey);
    }

    /**
     * 自减Hash中value的值
     *
     * @param key
     * @param id
     * @return
     */
    public Long decHash(final String key, final String id) {
        List<Long> txResults =
                transactionRedisTemplate.execute(
                        new SessionCallback<List<Long>>() {
                            @Override
                            public List<Long> execute(RedisOperations operations) throws DataAccessException {
                                operations.multi();
                                operations.opsForHash().increment(key, id, -1L);
                                return operations.exec();
                            }
                        });
        return txResults.isEmpty() ? 0L : txResults.get(0);
    }

    /**
     * 自增Hash中value的值
     *
     * @param key
     * @param id
     * @return
     */
    public Long incHash(final String key, final String id) {
        List<Long> txResults =
                transactionRedisTemplate.execute(
                        new SessionCallback<List<Long>>() {

                            @Override
                            public List<Long> execute(RedisOperations operations) throws DataAccessException {
                                operations.multi();
                                operations.opsForHash().increment(key, id, 1L);
                                return operations.exec();
                            }
                        });
        return txResults.isEmpty() ? 0L : txResults.get(0);
    }

    /**
     * 重置库存信息
     *
     * @param key
     * @param id
     */
    public void resetInventory(final String key, final String id) {
        transactionRedisTemplate.execute(
                new SessionCallback<List<Long>>() {

                    @Override
                    public List<Long> execute(RedisOperations operations) throws DataAccessException {
                        operations.multi();
                        operations.opsForHash().put(key, id, 0L);
                        return operations.exec();
                    }
                });
    }
}
