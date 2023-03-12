package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.mapper.MenuMapper;
import com.example.mapper.UserMapper;
import com.example.pojo.LoginUserDetails;
import com.example.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private MenuMapper menuMapper;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//查询用户信息
		LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
				.eq(User::getUserName, username);
		User user = userMapper.selectOne(queryWrapper);
		//没有查询到用户，给出对应的异常提示
		if (Objects.isNull(user)){
			throw new RuntimeException("用户名或密码错误"); //交给异常过滤器处理
		}
		//查询对应的权限信息
		// List<String> list = new ArrayList<>(Arrays.asList("test", "admin"));
		List<String> list = menuMapper.selectPermsByUserId(user.getId());
		//把数据封装成UserDetails返回
		return new LoginUserDetails(user, list);
	}
}
