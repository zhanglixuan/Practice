package com.example;

import com.example.mapper.MenuMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootTest
class SpringsecurityDemoApplicationTests {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private MenuMapper menuMapper;

	@Test
	void testSelectPermsByUserId(){
		List<String> strings = menuMapper.selectPermsByUserId(2L);
		System.out.println(strings);
	}

	@Test
	void testPasswordEncoder(){
		// System.out.println(passwordEncoder.encode("1234"));
		System.out.println(passwordEncoder.matches("1234",
				"$2a$10$uQeJGytbNuoAdqTB59iux.v48e7RT0AK6IJdwTGazNNHSCxIM/8be"));
	}

	@Test
	void testSelectUser() {
		List<User> users = userMapper.selectList(null);
		System.out.println(users);
	}

}
