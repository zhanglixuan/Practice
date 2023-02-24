package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
@Configuration
@ComponentScan("com.example.controller")
@EnableWebMvc  //开启由JSON数据转换成对象的功能
public class SpringMvcConfig {
}
