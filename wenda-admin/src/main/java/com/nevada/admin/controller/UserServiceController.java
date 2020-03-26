package com.nevada.admin.controller;


import com.nevada.admin.common.CommonResult;
import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserServiceController {

    @Autowired
    private UserService userService;

//    @Value("${jwt.tokenHeader}")
//    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation("注册")
    public CommonResult<User> register(@RequestBody UserDto userDto){

       User user=userService.register(userDto);
        if (user == null) {
            CommonResult.failed();
        }
        return CommonResult.success(user);
    }


//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public CommonResult login(String name, String password){
//         String token=userService.login(name, password);
//        if (token == null) {
//            return CommonResult.validateFailed("用户名或密码错误");
//        }
//        Map<String, String> tokenMap = new HashMap<>();
//        tokenMap.put("token", token);
//        tokenMap.put("tokenHead", tokenHead);
//        return CommonResult.success(tokenMap);
//    }

}
