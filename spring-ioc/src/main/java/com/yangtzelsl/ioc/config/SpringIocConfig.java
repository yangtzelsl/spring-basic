package com.yangtzelsl.ioc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration: 作为配置类，替代xml配置文件
 */
@Configuration
@ComponentScan(basePackages = {"com.yangtzelsl"})
public class SpringIocConfig {

}
