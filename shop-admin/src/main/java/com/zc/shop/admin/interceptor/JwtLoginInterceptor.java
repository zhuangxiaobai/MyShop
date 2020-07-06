package com.zc.shop.admin.interceptor;


import com.zc.shop.admin.mapper.UsersExtMapper;
import com.zc.shop.admin.util.JwtTokenUtil;
import com.zc.shop.common.api.ResultCode;
import com.zc.shop.common.exception.BusinessException;

import com.zc.shop.mbg.po.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class JwtLoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UsersExtMapper usersExtMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

   // private User user;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

                String path = request.getServletPath();//相对路径
                //   String absoutePath =  request.getRequestURI();//绝对路径
                System.out.println("当前请求路径：" + path);
                //  System.out.println("当前请求绝对路径：" + absoutePath);
                System.out.println("=========进入jwt登录拦截器===================");

                String authHeader = request.getHeader(this.tokenHeader);
                System.out.println("header:"+authHeader);
                if (authHeader != null){ //&& authHeader.startsWith(this.tokenHead)) {
                    String authToken =  authHeader;
                            //authHeader.substring(this.tokenHead.length());// The part after "Bearer "
                    String username = jwtTokenUtil.getUserNameFromToken(authToken);
                    System.out.println(authToken);
                    System.out.println("username:"+username);
                   // LOGGER.info("checking username:{}", username);
                    if (username != null ) {

                        Users user = this.usersExtMapper.selectUserByUserName(username);
                        System.out.println(user.toString());
                        if (jwtTokenUtil.validateToken(authToken, user)) {

//
                            return true;
                        }
                    }
                }

                throw  new BusinessException(ResultCode.UNAUTHORIZED);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }













}
