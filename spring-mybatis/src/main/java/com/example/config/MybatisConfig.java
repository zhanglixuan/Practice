package com.example.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
public class MybatisConfig {
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean ssfb = new SqlSessionFactoryBean();
		ssfb.setTypeAliasesPackage("com.example.domain");
		ssfb.setDataSource(dataSource);
		return ssfb;
	}

	/**
	 * 扫描配置，使用自动代理的形式创建接口的实现类的(注解形式开发)
	 * 或者找到接口对应的映射文件xml（手动编写的实现类，需要配置扫描包）
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer msc = new MapperScannerConfigurer();
		msc.setBasePackage("com.example.dao");
		return msc;
	}
}
