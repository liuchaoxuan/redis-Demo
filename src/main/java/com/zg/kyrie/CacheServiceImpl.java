package com.zg.kyrie;

import com.zg.kyrie.utils.SerializeUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by liuchaox on 5/30/2018.
 */
public class CacheServiceImpl implements CacheService {

    private static Log log = LogFactory.getLog(CacheServiceImpl.class);

    private RedisConnection redisConnection;

    private Integer dbIndex;

    public RedisConnection getRedisConnection() {
        return redisConnection;
    }

    public void setRedisConnection(RedisConnection redisConnection) {
        this.redisConnection = redisConnection;
    }

    public Integer getDbIndex() {
        return dbIndex;
    }

    public void setDbIndex(Integer dbIndex) {
        this.dbIndex = dbIndex;
    }

    @Override
    public void putObject(String key, Object value) {
        putObject(key, value, -1);
    }

    @Override
    public void putObject(String key, Object value, int expiration) {
        Jedis jedis = redisConnection.getJedis();
        if (jedis != null) {
            try {
                jedis.select(dbIndex);
                if (expiration < 0) {
                    jedis.set(key.getBytes(),SerializeUtil.serialize(value));
                } else {
                    jedis.setex(key.getBytes(), expiration, SerializeUtil.serialize(value));
                }
                log.info("set successfully for: " + key);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
    }

    @Override
    public Object pullObject(String key) {
        Jedis jedis = redisConnection.getJedis();
        if (jedis != null) {
            try {
                jedis.select(dbIndex);
                byte[] bytes = jedis.get(key.getBytes());
                Object object = SerializeUtil.unSerialize(bytes);
                log.info("get value successfully for: " + key);
                return object;
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                jedis.close();
            }
        }
        return null;
    }

    @Override
    public Long getExpireTime(String key) {
        return null;
    }

    @Override
    public boolean delObject(String key) {
        return false;
    }

    @Override
    public boolean expire(String key, int expireSecond) {
        Jedis jedis = redisConnection.getJedis();
        if (jedis != null) {
            jedis.select(dbIndex);
            long result = jedis.expire(key.getBytes(),expireSecond);
            if (result == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void clearObject() {

    }

    @Override
    public long ttl(String key) {
        Jedis jedis = redisConnection.getJedis();
        if (jedis != null) {
            jedis.select(dbIndex);
            long result = jedis.ttl(key.getBytes());
            if (result != -1) {
                return result;
            }
        }
        return -1;
    }
}
