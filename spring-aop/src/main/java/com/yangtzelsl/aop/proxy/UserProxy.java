package com.yangtzelsl.aop.proxy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserProxy {

    /**
     * 相同切入点抽取
     */
    @Pointcut(value = "execution(* com.yangtzelsl.aop.bean.User.add(..))")
    public void pointcut() {

    }

    /**
     * @Before 注解表示作为前置通知
     */
    @Before(value = "execution(* com.yangtzelsl.aop.bean.User.add(..))")
    public void before() {
        System.out.println("before......");
    }

    /**
     * 后置通知（返回通知）
     */
    @AfterReturning(value = "execution(* com.yangtzelsl.aop.bean.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning......");
    }

    /**
     * 最终通知
     */
    @After(value = "execution(* com.yangtzelsl.aop.bean.User.add(..))")
    public void after() {
        System.out.println("after.........");
    }

    /**
     * 异常通知
     */
    @AfterThrowing(value = "execution(* com.yangtzelsl.aop.bean.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing.........");
    }

    /**
     * 环绕通知
     *
     * @param proceedingJoinPoint
     * @throws Throwable
     */
    @Around(value = "execution(* com.yangtzelsl.aop.bean.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前.........");

        //被增强的方法执行
        proceedingJoinPoint.proceed();

        System.out.println("环绕之后.........");
    }

}
