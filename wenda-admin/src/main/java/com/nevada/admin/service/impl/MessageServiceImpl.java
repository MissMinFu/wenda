package com.nevada.admin.service.impl;


import com.nevada.admin.dao.MessageDao;
import com.nevada.admin.entity.Message;
import com.nevada.admin.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-13 14:39
 **/
public class MessageServiceImpl implements MessageService {

    @Autowired
    MessageDao messageDao;
    @Override
    public int addMessage(Message message) {
        return messageDao.addMessage(message)>0?message.getId():0;
    }
}
