package com.nevada.admin.interceptor;

import com.nevada.admin.dao.LoginTicketDao;
import com.nevada.admin.dao.UserDao;
import com.nevada.admin.domain.HostHolder;
import com.nevada.admin.entity.LoginTicket;
import com.nevada.admin.entity.User;
import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @program:wenda
 * @description:
 * @author: nevada
 * @create: 2020-04-14 15:08
 **/
public class PassortInterceptor implements HandlerInterceptor {

    @Autowired
    LoginTicketDao loginTicketDao;
    @Autowired
    UserDao userDao;
    @Autowired
    HostHolder hostHolder;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object c) throws Exception{
        String ticket=null;
        if(httpServletRequest.getCookies()!=null){
            for(Cookie cookie:httpServletRequest.getCookies()){
                if(cookie.getName().equals("ticket")){
                    ticket=cookie.getValue();
                    break;
                }
            }
        }
        if(ticket!=null){
            LoginTicket loginTicket=loginTicketDao.selectByTicket(ticket);
            if(loginTicket==null||loginTicket.getExpired().before(new Date())||loginTicket.getStatus()!=0){
                return  true;
            }
            User user=userDao.selectUserById(loginTicket.getUserId());
            hostHolder.setUser(user);
        }
        return true;
    }
}
