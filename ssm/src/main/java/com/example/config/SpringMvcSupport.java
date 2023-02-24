package com.example.config;

import com.example.controller.interceptor.ProjectInterceptor;
import com.example.controller.interceptor.ProjectInterceptor2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 张丽璇
 * @date 2023/2/23
 */
public class SpringMvcSupport implements WebMvcConfigurer {
	@Autowired
	private ProjectInterceptor projectInterceptor;
	@Autowired
	private ProjectInterceptor2 projectInterceptor2;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(projectInterceptor2).addPathPatterns("/books/**");
		registry.addInterceptor(projectInterceptor).addPathPatterns("/books/**");
	}
}
