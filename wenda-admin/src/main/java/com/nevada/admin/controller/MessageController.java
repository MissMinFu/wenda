package com.nevada.admin.controller;

import com.nevada.admin.async.EventProducer;
import com.nevada.admin.domain.HostHolder;
import com.nevada.admin.entity.Message;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.CommentService;
import com.nevada.admin.service.MessageService;
import com.nevada.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @program:wenda
 * @description:站内信
 * @author: nevada
 * @create: 2020-04-13 15:04
 **/

@RestController
public class MessageController {

    @Autowired
    HostHolder hostHolder;

    @Autowired
    UserService userService;

    @Autowired
    MessageService messageService;

    @RequestMapping(path={"/msg/lsit"},method = RequestMethod.GET)
    @ResponseBody
    public String addMessage(@RequestParam("toName") String toName,@RequestParam("contont") String content){
        try{
            if(hostHolder.getUser()==null){
                return "未登录";
            }
            User user=userService.getUserByName(toName);
            Message message=new Message();
            message.setFromId(userService.getCurrentMember().getId());
            message.setContent(content);
            message.setCreatedDate(new Date());
            message.setToId(user.getId());
            messageService.addMessage(message);
        }catch (Exception e){
            return "失败";
        }
        return "发送消息成功";
    }
}
