package com.nevada.admin.async.handler;

import com.nevada.admin.async.EventHandler;
import com.nevada.admin.async.EventModel;
import com.nevada.admin.async.EventType;
import com.nevada.admin.entity.Message;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.MessageService;
import com.nevada.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-13 14:30
 **/
public class LikeHandler implements EventHandler {

    @Autowired
    MessageService messageService;
    @Autowired
    UserService userService;
    @Override
    public void doHandler(EventModel model) {
        Message message=new Message();
        message.setFromId(0);
        message.setToId(model.getEntityOwnerId());
        message.setCreatedDate(new Date());
        User user=userService.getUser(model.getActorId());
        message.setContent("用户"+user.getName()+"赞了你的评论，"+"http://127.0.0.1:8081/question/"+model.getExts());
        messageService.addMessage(message);
    }

    @Override
    public List<EventType> getSupportEventTypes() {
        return Arrays.asList(EventType.LIKE);
    }
}
