package com.example.config;

import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@Configuration
@ComponentScan("com.example")
@Import({DruidConfig.class,MybatisConfig.class})
@PropertySource("classpath:jdbc.properties")
@EnableAspectJAutoProxy
@EnableTransactionManagement
public class SpringConfig {
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager ds= new DataSourceTransactionManager();
		ds.setDataSource(dataSource);
		return ds;
	}
}
