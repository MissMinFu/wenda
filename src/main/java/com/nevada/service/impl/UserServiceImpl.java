package com.nevada.service.impl;


import com.nevada.dao.UserDao;
import com.nevada.entity.User;
import com.nevada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private  UserDao userDao;

    public User register(String name,String password) {
        User user = new User();
//        user=UserDao.selectByName(username);
        user.setName(name);
        user.setPassword(password);
        userDao.insert(user);
        return user;
    }
}
