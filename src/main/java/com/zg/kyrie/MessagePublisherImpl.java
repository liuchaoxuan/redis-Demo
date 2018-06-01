package com.zg.kyrie;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;

/**
 * Created by liuchaox on 5/31/2018.
 */
public class MessagePublisherImpl implements MessagePublisher {

    public static Log log = LogFactory.getLog(MessagePublisherImpl.class);

    private RedisConnection redisConnection;
    private String[] chanels;

    public RedisConnection getRedisConnection() {
        return redisConnection;
    }

    public void setRedisConnection(RedisConnection redisConnection) {
        this.redisConnection = redisConnection;
    }

    public String[] getChanels() {
        return chanels;
    }

    public void setChanels(String[] chanels) {
        this.chanels = chanels;
    }

    @Override
    public void publishMessage(String message) {
        Jedis jedis = redisConnection.getJedis();
        if (jedis != null) {
            for (String chanel : chanels) {
                jedis.publish(chanel, "publish " + message + " from " + chanel);
                log.info(chanel + " is publishing message-------------");
            }
            jedis.close();
        }
    }
}
