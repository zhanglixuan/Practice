package com.example.controller;

import com.example.pojo.User;
import com.example.result.CodeUtil;
import com.example.result.Result;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/getAll")
	public Result getAll(){
		return new Result(CodeUtil.SUCCESS,userService.getAll(),"");
	}

	@PostMapping("/add")
	public Result addUser(User user){
		boolean b = userService.addUser(user);
		Result result = new Result();
		if (b){
			result.setCode(CodeUtil.SUCCESS);
			result.setMsg("添加成功");
		}else {
			result.setCode(CodeUtil.FAILURE);
			result.setMsg("添加失败");
		}
		return result;
	}
}
