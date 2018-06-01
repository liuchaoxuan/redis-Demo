package com.zg.kyrie.utils;

import com.zg.kyrie.RedisConnection;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by liuchaox on 5/30/2018.
 */
public class RedisConnectionUtil {
    private static RedisConnection redisConnection;
    public static RedisConnection create(){
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxTotal(100);
        jedisPoolConfig.setMaxIdle(10);
        jedisPoolConfig.setMinIdle(1);

        redisConnection = new RedisConnection();
        redisConnection.setHost("16.186.75.216");
        redisConnection.setPort(6379);
        redisConnection.setPassword("123456");
        redisConnection.setTimeout(1000);
        redisConnection.setClientName(Thread.currentThread().getName());
        redisConnection.setJedisPoolConfig(jedisPoolConfig);
        return redisConnection;
    }
}
