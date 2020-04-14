package com.nevada.admin.service;



import com.nevada.admin.common.CommonResult;
import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;


public interface UserService {


    @Transactional
    User register(UserDto userDto);

    String login(HttpServletResponse response,String name,String password);

    UserDetails loadUserByUsername(String username);

    CommonResult generateAuthCode(String telephone);

    @Transactional
    CommonResult updatePassword(String telephone, String password, String authCode);

    User getUser(int id);

    User getUserByName(String name);

    User getCurrentMember();



}
