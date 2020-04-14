package com.nevada.admin.controller;


import com.nevada.admin.common.CommonResult;
import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "用户管理")
@RequestMapping(value="/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

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
    public CommonResult login(@RequestParam HttpServletResponse response, @RequestParam String name, @RequestParam String password){
       String  token= userService.login(response,name,password);
        if ( token== null) {
            return CommonResult.validateFailed("用户名或密码错误");
        }
        Map<String,String> tokenMap=new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/updatepassword",method = RequestMethod.POST)
    @ResponseBody
    public CommonResult updatepassword(@RequestParam String telephone,
                                       @RequestParam String password,
                                       @RequestParam String authCode){

        return userService.updatePassword(telephone,password,authCode);
    }





}
