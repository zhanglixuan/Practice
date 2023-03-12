package com.example.service.impl;

import com.example.pojo.LoginUserDetails;
import com.example.pojo.User;
import com.example.service.LoginService;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import com.example.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
@Service
public class LoginServiceImpl implements LoginService {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private RedisCache redisCache;

	@Override
	public ResponseResult login(String username, String password) {
		//	AuthenticationManager authenticate进行用户认证
		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
		Authentication authenticate = authenticationManager.authenticate(authenticationToken);
		//如果认证没通过，给出对应的提示
		if (Objects.isNull(authenticate)){
			throw new RuntimeException("登录失败");
		}
		//如果认证通过了，使用userid生成一个jwt，jwt存入ResponseResult返回
		LoginUserDetails loginUserDetails = (LoginUserDetails) authenticate.getPrincipal();
		String userId = loginUserDetails.getUser().getId().toString();
		String jwt = JwtUtil.createJWT(userId);
		Map<String, String> map = new HashMap<>();
		map.put("token",jwt);
		//把完整的用户信息存入redis  userid作为key
		redisCache.setCacheObject("login:"+userId, loginUserDetails);
		return new ResponseResult(200,"登陆成功", map);
	}

	@Override
	public ResponseResult logout() {
		//获取SecurityContextHolder中的userid
		//是能够获取到SecurityContextHolder中的值的，因为当调用注销请求时，会先被Jwt认证过滤器拦截
		//获取并解析token然后去redis中获取对应的值，如果不存在直接被拦截了，存在则放行，执行请求资源对应的方法
		//并不需要删除SecurityContextHolder中的值，因为每一次请求都是新的请求，SecurityContextHolder中存放的值是不一样的
		UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
		LoginUserDetails loginUserDetails = (LoginUserDetails) authentication.getPrincipal();
		Long userId = loginUserDetails.getUser().getId();
		//删除redis中的值
		redisCache.deleteObject("login:"+userId);
		return new ResponseResult(200,"注销成功");
	}
}
