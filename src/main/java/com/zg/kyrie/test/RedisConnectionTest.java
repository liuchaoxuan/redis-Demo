package com.zg.kyrie.test;

import com.zg.kyrie.RedisConnection;
import org.junit.*;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by liuchaox on 5/30/2018.
 */
public class RedisConnectionTest {

    private RedisConnection redisConnection;

    @Before
    public void before(){
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
    }

    @Test
    public void test(){
        Jedis jedis = redisConnection.getJedis();
        if (jedis != null) {
           try {
               jedis.select(1);
               jedis.set("liu", "chaoxuan");
               Assert.assertEquals(jedis.get("liu"),"chaoxuan");
           } catch (Exception e) {
               e.printStackTrace();
           } finally {
               jedis.close();
           }
        }

    }
}
