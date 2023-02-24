package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * @author 张丽璇
 * @date 2023/2/22
 */
public class JdbcConfig {
	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource){
		DataSourceTransactionManager dt = new DataSourceTransactionManager();
		dt.setDataSource(dataSource);
		return dt;
	}
}
