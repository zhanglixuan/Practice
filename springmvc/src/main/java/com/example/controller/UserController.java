package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
@Controller //定义为bean，统一交个springmvc管理
@RequestMapping("/user") //定义整个模块的请求路径前缀
public class UserController {
	@RequestMapping("/save")
	@ResponseBody
	public String save(){
		System.out.println("user save...");
		return "{\"module\":\"user save...\"}";
	}

	@RequestMapping("/delete")
	@ResponseBody
	public String delete(){
		System.out.println("user delete...");
		return "{\"module\":\"user delete...\"}";
	}
}
