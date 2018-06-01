package com.zg.kyrie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;

/**
 * Created by liuchaox on 5/31/2018.
 */
public class MessageConsumerImpl {

    public static Log log = LogFactory.getLog(MessageConsumerImpl.class);

    public MessageConsumerImpl(RedisConnection redisConnections, String[] channels) {
        Jedis jedis = redisConnections.getJedis();
        jedis.subscribe(new JedisPubSub() {
            @Override
            public void onMessage(String channel, String message) {
                log.info("receive " + message + " from " + channel);
            }
        },channels);
    }
}
