package com.nevada.admin.bo;

import com.nevada.admin.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @program:wenda
 * @description: 自定义安全配置
 * @author: nevada
 * @create: 2020-03-26 10:25
 **/
public class AdminUserDetails implements UserDetails {

    User user ;

    //stream流
    public AdminUserDetails(User user){
        this.user=user;
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
