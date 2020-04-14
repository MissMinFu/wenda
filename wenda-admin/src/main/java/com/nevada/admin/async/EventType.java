package com.nevada.admin.async;

/**
 * @program:wenda
 * @description: 事件类型
 * @author: nevada
 * @create: 2020-04-13 11:38
 **/
public enum  EventType {

    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3),
    FOLLOW(4),
    UNFOLLOW(5);

    private int value;
    EventType(int value){
        this.value=value;
    }
    public int getValue(){
        return value;
    }
}
