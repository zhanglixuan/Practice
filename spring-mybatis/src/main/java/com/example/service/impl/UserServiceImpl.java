package com.example.service.impl;

import com.example.service.UserService;
import com.example.dao.UserDao;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	public List<User> selectAll() {
		return userDao.selectAll();
	}

	public User selectById(Integer id) {
		return userDao.selectById(id);
	}

	public Integer addUser(User user) {
		return userDao.addUser(user);
	}

	public Integer updateUser(Integer id, String username) {
		return userDao.update(id,username);
	}

	public Integer delete(Integer id) {
		return userDao.delete(id);
	}

	public void before(){
		System.out.println("时间："+System.currentTimeMillis());
		System.out.println("before...");
	}

	public int after(){
		System.out.println("after...");
		return 1;
	}
}
