package com.zhuanghou.videos.tool;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Map;

/**
 * Created by duhui on 2017/12/1.
 */
public class Redis extends Jedis {

    public Jedis redis;

    {
        redis = new Jedis("127.0.0.1", 6379);
        //redis.auth("root");
    }

    // public static void main(String[] args) {
    // System.out.println(redis.get("name"));
    // System.out.println(redis.keys("*"));
    // // redis.sinter(keys);
    // }

    public String get(String key) {
        return redis.get("name");
    }

    public String set(String key, String value) {
        return redis.set(key, value);
    }

    public Long del(String... keys) {
        return redis.del(keys);
    }

    // 键值增加字符
    public Long append(String key, String str) {
        return redis.append(key, str);
    }

    public Boolean exists(String key) {
        return redis.exists(key);
    }

    // Need research
    public Long setnx(String key, String value) {
        return redis.setnx(key, value);
    }

    public String setex(String key, String value, int seconds) {
        return redis.setex(key, seconds, value);
    }

    public Long setrange(String key, String str, int offset) {
        return redis.setrange(key, offset, str);
    }

    public List<String> mget(String... keys) {
        return redis.mget(keys);
    }

    public String mset(String... keys) {
        return redis.mset(keys);
    }

    public Long msetnx(String... keysvalues) {
        return redis.msetnx(keysvalues);
    }

    public String getset(String key, String value) {
        return redis.getSet(key, value);
    }

    public String hmset(String key, Map<String, String> hash) {
        return redis.hmset(key, hash);
    }

    public Map<String, String> hgetall(String key) {
        return redis.hgetAll(key);
    }

    public String hget(final String key, final String field) {
        return redis.hget(key, field);
    }

    public Long hset(final String key, final String field, final String value) {
        return redis.hset(key, field, value);
    }

    public Long expire(final String key, final int seconds) {
        return redis.expire(key, seconds);
    }

    public Boolean hexists(final String key, final String field) {
        return redis.hexists(key, field);
    }

}


