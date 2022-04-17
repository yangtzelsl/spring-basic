package com.yangtzelsl.aop;


import com.yangtzelsl.aop.bean.User;
import com.yangtzelsl.aop.config.ConfigAop;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopMain {
    @Test
    public void testAopAnnotation() {
        //加载配置类
        ApplicationContext context
                = new AnnotationConfigApplicationContext(ConfigAop.class);

        // 获取对象
        User user = context.getBean("user", User.class);

        // 调用方法
        user.add();
    }
}
