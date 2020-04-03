package com.nevada.admin.config;



import com.nevada.admin.bo.AdminUserDetails;
import com.nevada.admin.dao.UserDao;

import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.UserService;
import com.nevada.admin.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



/**
 * @program:wenda
 * @description: 配置
 * @author: nevada
 * @create: 2020-03-26 11:12
 **/

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Autowired
    UserDao userDao;
    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity
                .authorizeRequests();
        for (String url : ignoreUrlsConfig().getUrls()) {
            registry.antMatchers(url).permitAll();
        }
        registry
                .anyRequest()
                .authenticated()
//                .logout()
//                .logoutSuccessUrl("/")
//                .and()//记住密码功能
//                .rememberMe()
//                .tokenValiditySeconds(60*60*24)
//                .key("rememberMeKey")
                 .and()//关闭跨域伪造
                 .csrf()
                 .disable()
                .headers()//去除X-Frame-Options
                .frameOptions()
                .disable();
    }


    public void configure(AuthenticationManagerBuilder auth) throws  Exception{
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息

        return username -> userService.loadUserByUsername(username);

    }

    @Bean
    public IgnoreUrlsConfig ignoreUrlsConfig() {
        return new IgnoreUrlsConfig();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
}
    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

}