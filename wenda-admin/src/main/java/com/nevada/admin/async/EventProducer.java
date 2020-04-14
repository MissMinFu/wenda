package com.nevada.admin.async;

import com.nevada.admin.service.RedisService;
import com.nevada.admin.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program:wenda
 * @description: 生产者
 * @author: nevada
 * @create: 2020-04-08 16:50
 **/
@Service
public class EventProducer {

    @Autowired
    RedisService redisService;

    public boolean fireEvent(EventModel eventModel){
        try {
            String json=String.valueOf(eventModel);
            String key= RedisUtil.getEventQueueKey();
            redisService.set(key,json);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
