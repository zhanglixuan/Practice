package com.example.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 张丽璇
 * @date 2023/2/24
 */
@Configuration
public class MpConfig {
	@Bean
	public MybatisPlusInterceptor plusInterceptor(){
		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
		//添加分页拦截器
		interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
		//添加乐观锁拦截器
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		return interceptor;
	}
}
