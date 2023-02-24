package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
@Configuration
@ComponentScan("com.example.controller")
@Import({SpringMvcSupport.class})
@EnableWebMvc  //有很多功能，其中就是类型转换（JSON数据转换为对象）
public class SpringMvcConfig {
}
