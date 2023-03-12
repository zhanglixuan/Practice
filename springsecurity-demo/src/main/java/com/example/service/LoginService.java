package com.example.service;

import com.example.utils.ResponseResult;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
public interface LoginService {
	ResponseResult login(String username, String password);

	ResponseResult logout();
}
