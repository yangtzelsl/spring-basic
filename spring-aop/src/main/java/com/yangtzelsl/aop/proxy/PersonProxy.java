package com.yangtzelsl.aop.proxy;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 在增强类上面添加注解 @Order(数字类型值)，数字类型值越小，优先级越高
 */
@Component
@Aspect
@Order(1)
public class PersonProxy {

}
