package com.example.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//启动类会默认去扫描其所在包及其子包的文件
@SpringBootApplication
public class SpringbootJunitApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootJunitApplication.class, args);
	}

}
