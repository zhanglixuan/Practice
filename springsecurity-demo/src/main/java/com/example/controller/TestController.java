package com.example.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张丽璇
 * @date 2023/3/2
 */
@RestController
public class TestController {
	@RequestMapping("/hello")
	@PreAuthorize("hasAuthority('system:dept:list')")
	public String hello(){
		return "hello";
	}
}
