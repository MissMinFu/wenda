package com.nevada.admin.component;

import com.nevada.admin.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program:wenda
 * @description: 定时任务
 * @author: nevada
 * @create: 2020-04-08 11:34
 **/
@Component
public class ScheduleTask  {


    private Logger LOGGER = LoggerFactory.getLogger(ScheduleTask.class);
    @Autowired
    RedisService redisService;

    /**
     * cron表达式：Seconds Minutes Hours DayofMonth Month DayofWeek [Year]
     * 每10分钟扫描一次,将redis的数据保存到数据当中
     */
    @Scheduled(cron = "0 0/10 * ? * ?")
    private void save(){

        LOGGER.debug("");
    }



}
