package com.examle.user.service;

import com.example.user.UserApplication;
import com.example.user.pojo.User;
import com.example.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 张丽璇
 * @date 2023/2/25
 */
@SpringBootTest(classes = UserApplication.class)
public class UserServiceTest {
	@Autowired
	private UserService userService;
	@Test
	public void testQueryById(){
		User user = userService.queryById(1L);
		System.out.println(user);
	}
}
