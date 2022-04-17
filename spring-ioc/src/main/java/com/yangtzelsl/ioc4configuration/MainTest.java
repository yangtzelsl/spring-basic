package com.yangtzelsl.ioc4configuration;

import com.yangtzelsl.ioc4configuration.bean.Person;
import com.yangtzelsl.ioc4configuration.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MainTest {

    @SuppressWarnings("resource")
    public static void main(String[] args) {
        // XML配置文件方式
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Person bean1 = (Person) context.getBean("person");
        System.out.println(bean1);

        // 配置类方式
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainConfig.class);
        Person bean = applicationContext.getBean(Person.class);
        System.out.println(bean);

        String[] namesForType = applicationContext.getBeanNamesForType(Person.class);
        for (String name : namesForType) {
            System.out.println(name);
        }
    }
}
