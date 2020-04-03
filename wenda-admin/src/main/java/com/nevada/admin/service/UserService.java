package com.nevada.admin.service;



import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;



public interface UserService {

    User register(UserDto userDto);

    String login(String name,String password);



}
