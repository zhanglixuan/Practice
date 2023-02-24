package com.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
@Configuration
@ComponentScan({"com.example.service","com.example.aop"})
@Import({JdbcConfig.class})
@EnableTransactionManagement
@EnableAspectJAutoProxy
public class SpringConfigService {
}
