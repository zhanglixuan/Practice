package com.example.controller;

import com.example.result.CodeUtil;
import com.example.result.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
@RestController
public class LoginController {

	@PostMapping("/login")
	public Result login(@RequestParam String username, @RequestParam String password){
		try {
			Subject subject = SecurityUtils.getSubject();
			AuthenticationToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			//将sessionId作为token返回给前端
			Serializable sessionId = subject.getSession().getId();
			Map<String, String> data = new HashMap<>();
			data.put("token", sessionId.toString());
			System.out.println(data);
			return new Result(CodeUtil.SUCCESS, data,"登录成功");
		} catch (UnknownAccountException e) {
			e.printStackTrace();
			return new Result(CodeUtil.UNKNOWN_ERROR,null, "用户不存在");
		}catch (IncorrectCredentialsException e) {
			e.printStackTrace();
			return new Result(CodeUtil.UNKNOWN_ERROR,null, "密码错误");
		}catch (Exception e){
			e.printStackTrace();
			return new Result(CodeUtil.UNKNOWN_ERROR,null, "系统错误");
		}
	}

	@PostMapping("/logout")
	public Result logout(){
		try {
			System.out.println("logout success");
			Subject subject = SecurityUtils.getSubject();
			subject.logout();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Result(CodeUtil.SUCCESS, "退出成功");
	}
}
