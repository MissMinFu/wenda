package com.nevada.service;



import com.nevada.dto.UserDto;
import com.nevada.entity.User;

import java.util.List;


public interface UserService {

    void register(UserDto userDto);

    boolean login(String name, String password);

}
