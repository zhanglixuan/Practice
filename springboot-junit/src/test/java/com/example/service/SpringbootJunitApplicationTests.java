package com.example.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//会默认去加载同级下的引导类，也就是SpringBoot的配置环境
@SpringBootTest
class SpringbootJunitApplicationTests {
	@Autowired
	BookService bookService;

	@Test
	public void save() {
		bookService.save();
	}

}
