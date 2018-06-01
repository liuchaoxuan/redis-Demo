package com.zg.kyrie.test;

import com.zg.kyrie.MessagePublisherImpl;
import com.zg.kyrie.RedisConnection;
import com.zg.kyrie.utils.RedisConnectionUtil;
import org.junit.*;
import org.junit.Test;

/**
 * Created by liuchaox on 5/31/2018.
 */
public class RedisPubliserTest {
    private MessagePublisherImpl messagePublisher;

    @Before
    public void before() {
        RedisConnection redisConnection = RedisConnectionUtil.create();
        messagePublisher = new MessagePublisherImpl();
        messagePublisher.setRedisConnection(redisConnection);
        messagePublisher.setChanels(new String[]{"CHANNEL001", "CHANNEL002", "CHANNEL003"});
    }

    @Test
    public void publishMessage() {
        messagePublisher.publishMessage("-----test----");
    }
}
