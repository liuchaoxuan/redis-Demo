package com.zg.kyrie.test;

import redis.clients.jedis.Jedis;

/**
 * Created by liuchaox on 5/30/2018.
 */
public class Test {

    @org.junit.Test
    public void test() {
        //connect to Redis service, without password.
        Jedis jedis = new Jedis("16.186.75.216");
        System.out.println("connected successfully");
        //set redis string value
        jedis.set("runoobkey", "www.runoob.com");
        // get the stored value based on the key
        System.out.println("redis store strings: "+ jedis.get("runoobkey"));
    }
}