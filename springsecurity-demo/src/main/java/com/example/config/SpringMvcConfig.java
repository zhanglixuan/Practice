package com.example.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 张丽璇
 * @date 2023/3/6
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//设置允许跨越的路径
		registry.addMapping("/**")
				//设置允许跨越请求的域名
				.allowedOrigins("*")
				//是否允许cookie
				.allowCredentials(true)
				//设置允许的请求方式
				.allowedMethods("GET","POST","PUT", "DELETE")
				//跨域允许时间
				.maxAge(3600);
	}


}
