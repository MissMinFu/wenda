package com.nevada.admin.service;

import org.apache.ibatis.transaction.Transaction;


import java.util.List;

public interface RedisService {

    void like(int likedUserId,int entityId);

    void unLike(int likedUserId,int entityId);

    Long count(int entityId);

    void set(String key, Object value);

    String get(String key);

    boolean expire(String key, long expire);


    void remove(String key);

    Long increment(String key, long delta);
    Object brpop(int timeout,String key);

    Transaction multi(RedisService redisService);

    List<Object> exec(Transaction tx,RedisService redisService);

}
