package com.example.service;

import com.example.domain.User;

import java.util.List;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
public interface UserService {
	List<User> selectAll();
	User selectById(Integer id);
	Integer addUser(User user);
	Integer updateUser(Integer id, String username);
	Integer delete(Integer id);
	void before();
	int after();
}
