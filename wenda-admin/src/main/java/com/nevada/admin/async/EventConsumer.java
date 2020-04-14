package com.nevada.admin.async;

import com.nevada.admin.service.RedisService;
import com.nevada.admin.util.RedisUtil;
import javafx.application.Application;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program:wenda
 * @description: 消费者
 * @author: nevada
 * @create: 2020-04-08 16:53
 **/
public class EventConsumer implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Map<EventType, List<EventHandler>> config=new HashMap<>();

    @Autowired
    RedisService redisService;

    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String,EventHandler> beans=applicationContext.getBeansOfType(EventHandler.class);
        if(beans!=null){
            for(Map.Entry<String,EventHandler> entry:beans.entrySet()){
                List<EventType> eventTypes=entry.getValue().getSupportEventTypes();
                for(EventType type:eventTypes){
                    if(config.containsKey(type)){
                        config.put(type,new ArrayList<EventHandler>());
                    }
                    config.get(type).add(entry.getValue());
                }
            }
        }

        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                String key= RedisUtil.getEventQueueKey();
                List<String> events= (List<String>) redisService.brpop(0,key);
                for(String message:events){
                    if(message.equals(key)){
                        continue;
                    }
//                    EventModel eventModel=new EventModel();
//                    for(EventHandler handler:config.get(eventModel.getType())){
//                        handler.doHandler(eventModel);
//                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
