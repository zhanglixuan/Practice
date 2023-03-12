package com.example.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
@Configuration
public class MybatisPlusConfig {
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor(){
		MybatisPlusInterceptor plusInterceptor = new MybatisPlusInterceptor();
		plusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		plusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		return plusInterceptor;
	}
}
