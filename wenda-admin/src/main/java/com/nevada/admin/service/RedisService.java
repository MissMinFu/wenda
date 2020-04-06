package com.nevada.admin.service;

public interface RedisService {
    void like(int likedUserId,int entityId);

    void unLike(int likedUserId,int entityId);

    Long count(int entityId);

}
