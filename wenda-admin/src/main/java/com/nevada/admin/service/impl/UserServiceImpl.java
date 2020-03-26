package com.nevada.admin.service.impl;


import com.nevada.admin.bo.AdminUserDetails;
import com.nevada.admin.dao.UserDao;
import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(UserDto userDto) {

        User user = new User();

        //浅拷贝
        BeanUtils.copyProperties(userDto, user);
//        user.setName(userDto.getName());
//        user.setPhone(userDto.getPhone());
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        userDao.insert(user);
        return user;

    }

    public String login(String name, String password) {
        String token = null;

        User user = userDao.selectUser(name, password);
        if (password != user.getPassword()) {
            return " ";
        }
        return "登陆成功";

    }


    public User getAdminByUsername(String username) {
        UserDto userDto=new UserDto();
        User adminList = userDao.selectUser(username,userDto.getPassword());

        if (adminList != null ) {
            return adminList;
        }
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //获取用户信息
        User admin = getAdminByUsername(username);
        if (admin != null) {
            return new AdminUserDetails(admin);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

}
