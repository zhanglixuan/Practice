package com.example.service;

import com.example.config.SpringConfig;
import com.example.service.BookService;
import com.example.service.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
public class AppForAnnotation {
	public static void main(String[] args) {
		//读取java配置类获取spring容器
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		ctx.getBean(BookService.class).save();
		System.out.println(ctx.getBean(DataSource.class));
	}
}
