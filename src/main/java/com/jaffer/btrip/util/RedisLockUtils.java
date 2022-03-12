package com.jaffer.btrip.util;

import org.apache.commons.lang.BooleanUtils;
import redis.clients.jedis.Jedis;

/**
 * 分布式锁工具类
 */
public class RedisLockUtils {


    /**
     * 固定的超级是时间
     */
    private final static Integer EXPIRE_TIME = 60 * 5;


    /**
     * 加锁
     * @param key
     * @return
     */
    public static boolean tryLock(String key) {

        Jedis jedis = RedisUtils.getJedis();
        assert jedis != null;
        boolean exists = jedis.exists(key);

        if (BooleanUtils.isTrue(exists)) {
            //key 已经存在，证明已经有这个key了
            return false;
        }

        jedis.setex(key,EXPIRE_TIME,"true");
        return true;
    }

    /**
     * 解锁
     * @param key
     */
    public static void releaseLock(String key) {
        Jedis jedis = RedisUtils.getJedis();
        assert jedis != null;
        jedis.del(key);
    }
}
