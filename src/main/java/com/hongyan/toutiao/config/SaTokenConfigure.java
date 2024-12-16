package com.hongyan.toutiao.config;

import cn.dev33.satoken.interceptor.SaInterceptor;
import cn.dev33.satoken.stp.StpLogic;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SaToken的配置类
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    public static final String JWT_USER_ID_KEY = "userId";

    public static final String JWT_USERNAME_KEY = "username";

    public static final String JWT_ROLE_LIST_KEY = "roleCodes";

    public static final String JWT_CURRENT_ROLE_KEY = "currentRoleCode";

    /**
     * 创建并配置一个基于JWT的StpLogic bean
     * 该bean用于处理与JWT相关的用户登录和权限验证逻辑
     *
     * @return StpLogic实例，初始化为使用JWT方式
     */
    @Bean
    public StpLogic getStpLogicJwt() {
        return new StpLogic("jwt");
    }

    /**
     * 添加拦截器以配置请求的预处理
     * <p>
     * 此方法用于向应用程序添加全局拦截器，以在请求处理前执行特定逻辑
     * 具体来说，本方法添加了一个自定义拦截器，用于检查用户是否已登录
     * 拦截器将应用于所有请求路径，但某些特定路径被排除在外，以允许公开访问
     *
     * @param registry 拦截器注册表，用于添加和配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册一个SaInterceptor，用于在请求处理前检查用户登录状态
        registry.addInterceptor(new SaInterceptor(handle -> StpUtil.checkLogin()))
                // 应用所有路径
                .addPathPatterns("/**")
                // 排除登录路径，允许未登录用户访问
                .excludePathPatterns("/auth/login")
                // 排除验证码路径，允许未登录用户访问
                .excludePathPatterns("/auth/captcha")
                // 排除文档路径，允许公开访问
                .excludePathPatterns("/doc.html")
                // 排除WebJars相关路径，允许公开访问
                .excludePathPatterns("/webjars/**")
                // 排除网站图标路径，允许公开访问
                .excludePathPatterns("/favicon.ico")
                // 排除API文档路径，允许公开访问
                .excludePathPatterns("/v3/api-docs/**")
        ;
    }

}