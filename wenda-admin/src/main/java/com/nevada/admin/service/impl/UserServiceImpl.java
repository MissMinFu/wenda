package com.nevada.admin.service.impl;


import com.nevada.admin.dao.UserDao;
import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    @Resource
    private UserDao userDao;

    @Resource
    private PasswordEncoder passwordEncoder;

    public User register(UserDto userDto) {

        User user = new User();
        //浅拷贝
        BeanUtils.copyProperties(userDto,user);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setHeadUrl(" ");
        userDao.insert(user);
        return user;
    }
    public String login(String name,String password){

        User user = userDao.selectUser(name);
        if(!passwordEncoder.matches(password,user.getPassword())){
            return "";
        }
        return "登陆成功";
    }
}
