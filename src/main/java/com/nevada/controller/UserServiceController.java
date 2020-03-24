package com.nevada.controller;


import com.nevada.dto.UserDto;
import com.nevada.entity.User;
import com.nevada.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserServiceController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public void register(@RequestBody UserDto userDto){

       userService.register(userDto);
    }
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public boolean login(String name, String password){
        return userService.login( name, password);
    }

}
