[TOC]

# 前置知识

- Java 反射: [https://github.com/yangtzelsl/rpc-basic/tree/master/rpc-jdk-reflect](https://github.com/yangtzelsl/rpc-basic/tree/master/rpc-jdk-reflect)
- Jdk 动态代理: [https://github.com/yangtzelsl/rpc-basic/tree/master/rpc-jdk-dynamic-proxy](https://github.com/yangtzelsl/rpc-basic/tree/master/rpc-jdk-dynamic-proxy)

# Spring IOC
## 1、什么是 IOC
- （1）控制反转，把对象创建和对象之间的调用过程，交给 Spring 进行管理
- （2）使用 IOC 目的：为了耦合度降低

## 2、IOC 底层原理
（1）xml 解析、工厂模式、反射

![图片1](images/图1.png)

![图片2](images/图2.png)

## 3、注解

### 3.1、什么是注解
- （1）注解是代码特殊标记，格式：@注解名称(属性名称=属性值, 属性名称=属性值..)
- （2）使用注解，注解作用在类上面，方法上面，属性上面
- （3）使用注解目的：简化 xml 配置

### 3.2、Spring 针对 Bean 管理中创建对象提供注解

- （1）@Component
- （2）@Service
- （3）@Controller
- （4）@Repository

* 上面四个注解功能是一样的，都可以用来创建 bean 实例

### 3.3 基于注解方式实现属性注入

- （1）@Autowired：根据属性类型进行自动装配
- （2）@Qualifier：根据名称进行注入
- （3）@Resource：可以根据类型注入，可以根据名称注入
- （4）@Value：注入普通类型属性

- 注意：@Resource注解不是Spring官方的，而是javax.annotation.Resource,因此Spring不是很推荐

# Spring AOP
## 一、创建类，在类中定义方法
```java
public class User {
    public void add(){
        System.out.println("add() in User...");
    }
}
```
## 二、创建增强类，编写增强逻辑
```java
public class UserProxy {
//    前置通知
    public void before(){
        System.out.println("before......");
    }
}
```
## 三、进行通知的配置
### 3.1 在Spring配置文件中，开启注解扫描。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
<!--开启扫描注解-->
    <context:component-scan base-package="cn.ac.spring5.aspectj"></context:component-scan>
</beans>
```

### 3.2 使用注解创建User、UserProxy对象
```java
@Component
public class User {
    public void add(){
        System.out.println("add() in User...");
    }
}
```
```java
@Component
public class UserProxy {
//    前置通知
    public void before(){
        System.out.println("before......");
    }
}
```

### 3.3 在增强类上面添加注解@Aspect
```java
@Component
@Aspect //生成代理对象
public class UserProxy {
//    前置通知
    public void before(){
        System.out.println("before......");
    }
}
```
### 3.4 在spring配置文件中开启生成代理对象。
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">
<!--开启扫描注解-->
    <context:component-scan base-package="cn.ac.spring5.aspectj"></context:component-scan>
<!--开启AspectJ生成代理对象-->
    <aop:aspectj-autoproxy></aop:aspectj-autoproxy>
</beans>
```
## 四、配置不同类型的通知

- 在增强类中，在作为通知方法上面添加通知类型注解，使用切入点表达式配置。

```java
@Component
@Aspect
public class UserProxy {
//    前置通知
//    @Before 注解表示作为前置通知
    @Before(value = "execution(* cn.ac.spring5.aspectj.User.add(..))")
    public void before(){
        System.out.println("before......");
    }

//    后置通知（返回通知）
    @AfterReturning(value = "execution(* cn.ac.spring5.aspectj.User.add(..))")
    public void afterReturning(){
        System.out.println("afterReturning......");
    }
}
```

## 五、 相同切入点抽取
- 写通知时候，value值会有很多重复的，所以可以抽取如下

```java
@Component
@Aspect //生成代理对象
public class UserProxy {

//  相同切入点抽取
    @Pointcut(value = "execution(* cn.ac.spring5.aspectj.User.add(..))")
    public void pointcut(){

    }
//    前置通知
//    @Before 注解表示作为前置通知
    @Before(value = "pointcut()")
    public void before(){
        System.out.println("before......");
    }

//    后置通知（返回通知）
    @AfterReturning(value = "pointcut()")
    public void afterReturning(){
        System.out.println("afterReturning......");
    }
}
```

## 六、 多个增强类对同一个方法进行增强，可以设置增强类的优先级
- 在增强类上面添加注解 @Order(数字类型值)，数字类型值越小，优先级越高。

```java

@Componet
@Aspect
@Order(1)
public class PersonProxy{
	...
}
```

## 七、完全注解开发
不需要创建xml配置文件，创建配置类
```java
@Configuration
@ComponentScan(basePackages={"cn.ac.spring5"})
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ConfigAop{
	...
}
```

## 参考文档
[https://blog.csdn.net/xxnz123/article/details/123863911](https://blog.csdn.net/xxnz123/article/details/123863911)
