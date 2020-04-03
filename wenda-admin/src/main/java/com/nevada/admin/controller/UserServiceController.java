package com.nevada.admin.controller;


import com.nevada.admin.common.CommonResult;
import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserServiceController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult<User> register(@RequestBody UserDto userDto){

       User user=userService.register(userDto);
        if (user == null) {
            CommonResult.failed();
        }
        return CommonResult.success(user);
    }

    @ApiOperation(value = "登陆")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@RequestParam String name,@RequestParam String password){
       String  str= userService.login(name,password);
        if (str == null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        return CommonResult.success(str);
    }





}
