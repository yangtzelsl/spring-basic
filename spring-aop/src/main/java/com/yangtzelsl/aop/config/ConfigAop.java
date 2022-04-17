package com.yangtzelsl.aop.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * 不需要创建xml配置文件，创建配置类
 * @ComponentScan: <context:component-scan base-package="com.yangtzelsl.aop"></context:component-scan>
 * @EnableAspectJAutoProxy: <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
 */
@Configuration
@ComponentScan(basePackages = {"com.yangtzelsl.aop"})
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class ConfigAop {

}
