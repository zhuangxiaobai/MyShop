
package com.zc.shop.admin.config;

import com.zc.shop.admin.interceptor.JwtLoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private JwtLoginInterceptor jwtLoginInterceptor;



/**
     * 拦截器配置
     * @param registry
     */

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截器
       // InterceptorRegistration interceptorRegistry=registry.addInterceptor(new LoginInterceptor());
         InterceptorRegistration interceptorRegistry=registry.addInterceptor(jwtLoginInterceptor);
        //用户登录，注册不拦截
        interceptorRegistry.addPathPatterns("/**") //需要拦截的路径
        .excludePathPatterns("/user/login")     //不需要拦截的路径
        .excludePathPatterns("/user/register")
                .excludePathPatterns("/error")
                .excludePathPatterns("/user/forgetPassword")
                .excludePathPatterns("/user/sendMessage")
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**");//不拦截swagger相关资源








    }




}

