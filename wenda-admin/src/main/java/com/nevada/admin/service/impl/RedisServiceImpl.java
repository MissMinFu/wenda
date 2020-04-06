package com.nevada.admin.service.impl;

import com.nevada.admin.service.RedisService;
import com.nevada.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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
}
