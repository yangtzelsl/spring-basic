package com.yangtzelsl.ioc.config;

import com.yangtzelsl.ioc.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration: 作为配置类，替代xml配置文件
 */
@Configuration
@ComponentScan(basePackages = {"com.yangtzelsl"})
public class SpringIocConfig {

    /**
     * 给容器中注册一个Bean,类型为返回值的类型,id默认使用方法名
     *
     * @return 返回类型
     */
    @Bean
    public Person person() {
        return new Person("lisi", 20);
    }

}
