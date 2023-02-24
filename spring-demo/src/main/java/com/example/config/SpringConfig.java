package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@Configuration
@ComponentScan({"com.example.service","com.example.dao"})
@PropertySource({"classpath:jdbc.properties"}) //不支持通配符*
@Import({JdbcConfig.class})
public class SpringConfig {


}
