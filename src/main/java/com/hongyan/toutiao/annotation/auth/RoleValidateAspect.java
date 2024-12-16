package com.hongyan.toutiao.annotation.auth;

import cn.dev33.satoken.stp.StpUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * 角色检查切面
 */
@Aspect
@Component
public class RoleValidateAspect {


    @Pointcut("@annotation(Roles)")
    public void pointcut() {

    }


    /**
     * 在执行特定方法之前进行的角色权限检查
     * 此方法利用AOP（面向切面编程）来拦截带有特定注解的方法执行前的动作
     * 主要目的是检查当前操作用户是否具有足够的角色权限来执行该方法
     *
     * @param joinPoint 切入点对象，提供了关于被拦截方法的信息
     */
    @Before("pointcut()")
    public void before(JoinPoint joinPoint) {
        // 获取被拦截方法的签名
        Signature signature = joinPoint.getSignature();
        // 检查签名是否为方法签名，以便我们能够访问方法的详细信息
        if (signature instanceof MethodSignature methodSignature) {
            // 获取方法上可能存在的Roles注解
            Roles roles = methodSignature.getMethod().getAnnotation(Roles.class);
            // 检查当前用户是否具有注解中指定的角色权限
            StpUtil.checkRoleOr(roles.value());
        }
    }
}
