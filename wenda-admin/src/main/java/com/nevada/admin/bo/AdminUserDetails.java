package com.nevada.admin.bo;

import com.nevada.admin.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;

/**
 * @program:wenda
 * @description: 自定义安全配置
 * @author: nevada
 * @create: 2020-03-26 10:25
 **/
public class AdminUserDetails implements UserDetails {

    private User user ;

    public AdminUserDetails(User user){
        this.user=user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  Arrays.asList(new SimpleGrantedAuthority("nevada"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser(){
        return user;
    }
}
