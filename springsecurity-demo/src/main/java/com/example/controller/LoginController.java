package com.example.controller;

import com.example.service.LoginService;
import com.example.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
@RestController
public class LoginController {
	@Autowired
	private LoginService loginService;

	@PostMapping("/user/login")
	public ResponseResult login(@RequestParam String username, @RequestParam String password){
		return loginService.login(username, password);
	}

	@RequestMapping("/user/logout")
	public ResponseResult logout(){
		return loginService.logout();
	}
}
