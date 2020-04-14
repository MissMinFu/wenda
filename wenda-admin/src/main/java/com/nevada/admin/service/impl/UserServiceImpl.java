package com.nevada.admin.service.impl;


import com.nevada.admin.bo.AdminUserDetails;
import com.nevada.admin.common.CommonResult;
import com.nevada.admin.dao.UserDao;
import com.nevada.admin.domain.UserExample;
import com.nevada.admin.dto.UserDto;
import com.nevada.admin.entity.User;
import com.nevada.admin.service.RedisService;
import com.nevada.admin.service.UserService;

import com.nevada.admin.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    UserExample    userExample;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Resource
    private PasswordEncoder passwordEncoder;
    @Autowired
    RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;



    public User register(UserDto userDto) {

        User user = new User();
        //浅拷贝
        BeanUtils.copyProperties(userDto,user);
        String encodePassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodePassword);
        user.setHeadUrl(" ");
        userDao.insert(user);
        String tel=userDto.getPhone();
        generateAuthCode(tel);  //注册的时候生成验证码
        return user;
    }
    @Override
    public CommonResult generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for(int i=0;i<6;i++){
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE+telephone,sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE+telephone,AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString(),"获取验证码成功");
    }

    /**
     * 用redis作为缓存token服务器，解决分布式session
     * @param response
     * @param name
     * @param password
     * @return
     */
    public String login(HttpServletResponse response,String name,String password){

        String token = null;
        User user=userDao.selectUser(name);
        //密码需要客户端加密后传递
        try {
            UserDetails userDetails = loadUserByUsername(name);
            if(!passwordEncoder.matches(password,userDetails.getPassword())){
                throw new BadCredentialsException("密码不正确");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            addCookie(response,token,user);
//            updateLoginTimeByUsername(username);
//            insertLoginLog(username);
        } catch (AuthenticationException e) {
            LOGGER.warn("登录异常:{}", e.getMessage());
        }
        return token;
    }

    @Transactional
    public CommonResult updatePassword(String telephone, String password, String authCode){
        UserExample  example = new UserExample();
        example.createCriteria().andPhoneEqualTo(telephone);
        List<User> list=userDao.selectExample(example);
        if(CollectionUtils.isEmpty(list)){
            CommonResult.failed("该账号不存在");
        }
        if(!verifyAuthCode(authCode,telephone)){
            return CommonResult.failed("验证码错误");
        }
        User user=list.get(0);
        user.setPassword(passwordEncoder.encode(password));
        userDao.updatePassword(user);
        return CommonResult.success(null,"密码修改成功");
    }

    @Override
    public User getUser(int id) {
        return userDao.selectUserById(id);
    }

    public User getUserByName(String name){
        return userDao.selectUser(name);
    }

    /**
     * 获取当前登陆的用户
     * @return
     */

    public User getCurrentMember(){
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();
        AdminUserDetails details= (AdminUserDetails) auth.getPrincipal();
        return details.getUser();
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User umsAdminList = userDao.selectUser(username);
        if (umsAdminList != null) {
            return new AdminUserDetails(umsAdminList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    private boolean verifyAuthCode(String authCode, String telephone){
        if(StringUtils.isEmpty(authCode)){
            return false;
        }
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return realAuthCode.equals(authCode);
    }


    private void addCookie(HttpServletResponse response, String token, User user){
        redisService.set(token,user);
        Cookie cookie=new Cookie("token",token);
        cookie.setMaxAge(5); //token过期时间
        cookie.setPath("/");
        response.addCookie(cookie);

    }

}
