package com.nevada.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program:wenda
 * @description: 测试
 * @author: nevada
 * @create: 2020-03-25 14:37
 **/
public class HelloController {

    @RequestMapping("/")
    public String helloworld(){
        return "Hello world!";
    }

}
