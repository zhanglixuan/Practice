package com.example.controller;

import com.example.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
@Controller
@RequestMapping("/book")
public class BookController {
	@RequestMapping("/save")
	@ResponseBody
	public String save(@RequestParam("name") String username, String password){
		System.out.println("book save-->"+username+","+password);
		return "{'module':'book save'}";
	}

	@RequestMapping("/pojoParam")
	@ResponseBody
	public String pojoParam(@RequestBody User user){
		System.out.println(user.toString());
		return "{'module':'book save'}";
	}

	@RequestMapping("/pojoParamContainPojo")
	@ResponseBody
	public String pojoParamContainPojo(User user){
		System.out.println(user.toString());
		return "{'module':'book save'}";
	}

	@RequestMapping("/arrayParam")
	@ResponseBody
	public String arrayParam(String[] likes){
		System.out.println(Arrays.toString(likes));
		return "{'module':'book save'}";
	}
}
