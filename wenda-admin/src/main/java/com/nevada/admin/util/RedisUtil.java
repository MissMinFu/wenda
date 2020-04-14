package com.nevada.admin.util;

/**
 * @program:wenda
 * @description: 关注和点赞功能
 * @author: nevada
 * @create: 2020-04-05 14:39
 **/
public class RedisUtil {

    public  static String BIZ_LIKE="LIKE";
    public  static String BIZ_DISLIKE="DISLIKE";

    public static String getLikeKey(int entityId){
        StringBuilder builder=new StringBuilder();
        builder.append(BIZ_LIKE);
        builder.append(":");
        builder.append(entityId);
        return builder.toString();
    }

    public static String getDisLikeKey(int entityId){
        StringBuilder builder=new StringBuilder();
        builder.append(BIZ_DISLIKE);
        builder.append(":");
        builder.append(entityId);
        return builder.toString();
    }


    public static String getEventQueueKey(){
        return "EVENT_QUEUE";
    }



}
