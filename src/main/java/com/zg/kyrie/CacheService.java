package com.zg.kyrie;

/**
 * Created by liuchaox on 5/30/2018.
 */
public interface CacheService {

    /**
     * store the data to cache
     * @param key the key of data
     * @param value the value of data
     */
    void putObject(String key, Object value);

    /**
     * store the object and set expire time
     * @param key
     * @param value
     * @param expiration unit is second
     */
    void putObject(String key, Object value, int expiration);

    /**
     * get the object based on the key
     * @param key
     * @return
     */
    Object pullObject(String key);

    /**
     * get the expire time based on the key, return -2 if the key doesnt exist, and if the key doesnt has an expiration time, will return -1.
     * @param key
     * @return
     */
    Long getExpireTime(String key);

    /**
     * delete the object based on the key.
     * @param key
     * @return
     */
    boolean delObject(String key);

    /**
     * set the expire time based on the key
     * @param key
     * @param expireSecond
     * @return
     */
    boolean expire(String key, int expireSecond);

    /**
     * clear all the objects
     */
    void clearObject();

    long ttl(String key);
}
