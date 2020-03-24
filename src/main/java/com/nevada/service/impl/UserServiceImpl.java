package com.nevada.service.impl;


import com.nevada.dao.UserDao;
import com.nevada.dto.UserDto;
import com.nevada.entity.User;
import com.nevada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private  UserDao userDao;

    public void register( UserDto userDto) {

        userDao.insert(userDto);

    }

    public boolean login(String name, String password) {

        User user =userDao.selectUser(name,password);
        if(password==user.getPassword()){
           return false;
        }
        return true;

    }
}
