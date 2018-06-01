package com.zg.kyrie.test;

import com.zg.kyrie.CacheServiceImpl;
import com.zg.kyrie.RedisConnection;
import com.zg.kyrie.utils.RedisConnectionUtil;
import com.zg.kyrie.domain.HotProduct;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Created by liuchaox on 5/31/2018.
 */
@FixMethodOrder(MethodSorters.JVM)
public class RedisCacheTest {
    private RedisConnection redisConnection;
    private CacheServiceImpl cacheServiceImpl;
    private HotProduct hotProduct;
    public static Log log = LogFactory.getLog(RedisCacheTest.class);

    @Before
    public void before() {
        redisConnection = RedisConnectionUtil.create();
        cacheServiceImpl = new CacheServiceImpl();
        cacheServiceImpl.setRedisConnection(redisConnection);
        cacheServiceImpl.setDbIndex(1);
        hotProduct = new HotProduct();
        hotProduct.setId(1001);
        hotProduct.setName("xxxx");
        hotProduct.setDesc("THIS IS FOR TEST");
    }

    @Test
    public void test() {
        cacheServiceImpl.putObject(hotProduct.getName(), hotProduct);
        HotProduct result = (HotProduct)cacheServiceImpl.pullObject(hotProduct.getName());
        Assert.assertEquals(hotProduct.getName(), result.getName());
    }

    @Test
    public void testExpire(){
        boolean result = cacheServiceImpl.expire(hotProduct.getName(), 10);
        Assert.assertTrue(result);
    }

    @Test
    public void testTtl() {
        long result = cacheServiceImpl.ttl(hotProduct.getName());
        log.info(hotProduct.getName() + " will expire in " + result + " seconds");
    }
}
