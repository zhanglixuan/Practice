package com.example.controller;

import com.example.domain.Enterprise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* @author 张丽璇
* @date 2023/2/24
*/
@RestController
@RequestMapping("/books")
public class BookController {
	@Value("${lesson}")
	private String lesson;
	@Value("${server.port}")
	private Integer port;
	@Value("${enterprise.subject[1]}")
	private String subject;

	@Autowired
	private Environment environment;

	@Autowired
	private Enterprise enterprise;

	@GetMapping("/{id}")
	public String getById(@PathVariable Integer id){
		System.out.println("id===>"+id);
		System.out.println(lesson);
		System.out.println(port);
		System.out.println(subject);
		System.out.println("----------------------------");
		System.out.println(environment.getProperty("lesson"));
		System.out.println(environment.getProperty("server.port"));
		System.out.println(environment.getProperty("enterprise.subject[0]"));
		System.out.println("=================================");
		System.out.println(enterprise.getName());
		System.out.println(enterprise.getAge());
		System.out.println(enterprise.getSubject()[2]);
		return "hello springboot!";
	}
}
