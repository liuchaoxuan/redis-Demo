package com.zg.kyrie.test;

import com.zg.kyrie.MessageConsumerImpl;
import com.zg.kyrie.RedisConnection;
import com.zg.kyrie.utils.RedisConnectionUtil;
import org.junit.*;

/**
 * Created by liuchaox on 5/31/2018.
 */
public class RedisConsumerTest {

    @org.junit.Test
    public void testConsumer() {
        RedisConnection redisConnection = RedisConnectionUtil.create();
        String[] channels = new String[]{"CHANNEL001", "CHANNEL002", "CHANNEL003"};
        MessageConsumerImpl messageConsumer = new MessageConsumerImpl(redisConnection,channels);
    }
}
