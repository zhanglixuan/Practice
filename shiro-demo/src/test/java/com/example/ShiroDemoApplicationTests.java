package com.example;

import com.example.pojo.User;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroDemoApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	void testAddUser() {
		User user = new User()
				.setId(1)
				.setUsername("zhangsan")
				.setRealName("张三")
				.setPassword("1234");
		boolean b = userService.addUser(user);
		System.out.println(b);
	}

}
