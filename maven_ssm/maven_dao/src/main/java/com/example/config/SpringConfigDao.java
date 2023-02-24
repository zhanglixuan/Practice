package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
@Configuration
@ComponentScan({"com.example.dao"})
@PropertySource("classpath:jdbc.properties")
@Import({DruidConfig.class, MybatisConfig.class})
public class SpringConfigDao {
}
