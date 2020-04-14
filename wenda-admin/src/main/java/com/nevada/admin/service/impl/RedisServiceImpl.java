package com.nevada.admin.service.impl;

import com.nevada.admin.service.RedisService;
import com.nevada.admin.util.RedisUtil;
import org.apache.ibatis.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @program:wenda
 * @description: redis实现类
 * @author: nevada
 * @create: 2020-04-06 11:03
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void like(int userId, int entityId) {
        String key= RedisUtil.getLikeKey(entityId);
        redisTemplate.opsForSet().add(key,String.valueOf(userId));

        String disLikekey=RedisUtil.getDisLikeKey(entityId);
        redisTemplate.opsForSet().remove(disLikekey,String.valueOf(entityId));
    }

    @Override
    public void unLike(int unLikedUserId, int entityId) {
        String key= RedisUtil.getDisLikeKey(entityId);
        redisTemplate.opsForSet().add(key,String.valueOf(unLikedUserId));

        String likeKey= RedisUtil.getLikeKey(entityId);
        redisTemplate.opsForSet().remove(likeKey,String.valueOf(entityId));
    }

    public Long count(int entityId){
        String key= RedisUtil.getDisLikeKey(entityId);
        return redisTemplate.opsForSet().size(key);
    }

    @Override
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    @Override
    public boolean expire(String key, long expire) {
        return redisTemplate.expire(key, expire, TimeUnit.SECONDS);
    }

    @Override
    public void remove(String key) {
        redisTemplate.delete(key);
    }

    @Override
    public Long increment(String key, long delta) {
        return redisTemplate.opsForValue().increment(key,delta);
    }

    public Object brpop(int timeout, String key){

       return redisTemplate.opsForList().leftPop(key);
    }
    public Transaction multi(RedisService redisService){
        return redisService.multi(redisService);
    }
    public List<Object> exec(Transaction tx,RedisService redisService){
        return redisService.exec(tx,redisService);
    }


}
