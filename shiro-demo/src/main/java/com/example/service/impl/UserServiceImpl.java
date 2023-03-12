package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.dao.UserDao;
import com.example.pojo.User;
import com.example.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author 张丽璇
 * @date 2023/2/28
 */
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public User findByName(String username) {
		LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda()
				.eq(StringUtils.isNoneBlank(username), User::getUsername, username);
		return userDao.selectOne(queryWrapper);
	}

	@Override
	public boolean addUser(User user) {
		String salt = RandomStringUtils.randomNumeric(6, 8);
		Md5Hash md5Hash = new Md5Hash(user.getPassword(),salt,1); //模拟MD5加密1次
		user.setPassword(md5Hash.toString());
		user.setPrivateSalt(salt);
		return userDao.insert(user) > 0;
	}

	@Override
	public List<User> getAll() {
		return userDao.selectList(null);
	}

	@Override
	public Set<String> getRoleByName(String username){
		return userDao.getRoleByName(username);
	}
}
