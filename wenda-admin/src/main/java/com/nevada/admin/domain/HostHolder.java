package com.nevada.admin.domain;

import com.nevada.admin.entity.User;
import org.springframework.stereotype.Component;


/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-13 15:05
 **/
@Component
public class HostHolder {
    //threadlocal保存一份本地变量
    private static ThreadLocal<User> users=new ThreadLocal<>();
    public User getUser(){
        return users.get();
    }

    public void setUser(User user){
        users.set(user);
    }
    public void clear(){
        users.remove();
    }
}
