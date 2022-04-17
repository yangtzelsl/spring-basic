package com.yangtzelsl.ioc;


import com.yangtzelsl.ioc.bean.Emp;
import com.yangtzelsl.ioc.config.SpringIocConfig;
import com.yangtzelsl.ioc.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringIocMain {

    @Test
    public void testXmlBean1() {
        //1 加载spring配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean2.xml");

        //2 获取配置创建的对象
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }

    @Test
    public void testXmlBean2() {
        //1 加载spring配置文件
        ApplicationContext context =
                new ClassPathXmlApplicationContext("bean4.xml");

        //2 获取配置创建的对象
        Emp emp = context.getBean("emp", Emp.class);

        emp.add();
    }

    /**
     * 使用配置类测试
     */
    @Test
    public void testConfigBean() {
        //1 加载spring配置文件
        ApplicationContext context =
                new AnnotationConfigApplicationContext(SpringIocConfig.class);

        //2 获取配置创建的对象
        Emp emp = context.getBean("emp", Emp.class);

        emp.add();
    }
}
