package com.nevada.admin.dao;

import com.nevada.admin.entity.LoginTicket;

/**
 * @program:wenda
 * @description: 登陆票据
 * @author: nevada
 * @create: 2020-04-14 15:09
 **/
public interface LoginTicketDao {

    LoginTicket selectByTicket(String ticket);
}
