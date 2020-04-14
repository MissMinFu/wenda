package com.nevada.admin.interceptor;

import com.nevada.admin.domain.HostHolder;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program:wenda
 * @description: 登陆拦截器
 * @author: nevada
 * @create: 2020-04-14 14:59
 **/

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    @Autowired
    HostHolder hostHolder;
    public boolean preHandle(HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse,Object c) throws Exception{
        if(hostHolder.getUser()==null){
            httpServletResponse.sendRedirect("/relogin?next="+httpServletRequest.getRequestURI());
        }
        return true;
    }
    public void  postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception{}
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {}
}
