package com.examle.user.dao;

import com.example.user.UserApplication;
import com.example.user.mapper.UserMapper;
import com.example.user.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author 张丽璇
 * @date 2023/2/26
 */
@SpringBootTest(classes = UserApplication.class)
public class UserDaoTest {
	@Autowired
	private UserMapper userMapper;

	@Test
	public void testGetById(){
		User user = userMapper.selectById(1L);
		System.out.println(user);
	}
}
