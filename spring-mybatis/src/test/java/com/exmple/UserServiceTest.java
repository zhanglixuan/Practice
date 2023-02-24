package com.exmple;

import com.example.config.SpringConfig;
import com.example.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@RunWith(SpringJUnit4ClassRunner.class) //指定为spring整合junit特有的运行器
@ContextConfiguration(classes = SpringConfig.class) //使用bean得指定配置类在哪
public class UserServiceTest {
	@Autowired
	UserService userService;

	@Test
	public void testUserService(){
		System.out.println(userService.selectById(2));
	}
}
