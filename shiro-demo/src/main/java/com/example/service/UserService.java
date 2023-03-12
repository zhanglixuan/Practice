package com.example.service;

import com.example.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
public interface UserService {
	User findByName(String username);

	boolean addUser(User user);

	List<User> getAll();

	Set<String> getRoleByName(String username);
}
