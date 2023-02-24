package com.example.service;

import com.example.config.SpringConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		// UserService userService = ctx.getBean(UserService.class);
		// userService.selectAll();
		// System.out.println(userService.getClass());
		//从IoC容器中获取对象
		AccountService accountService = ctx.getBean(AccountService.class);
		Double money=100d;
		accountService.transfer(1,2,money);

	}
}
