package com.example.filter;

import com.example.pojo.LoginUserDetails;
import com.example.utils.JwtUtil;
import com.example.utils.RedisCache;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
* @author 张丽璇
* @date 2023/3/5
 * 不去实现默认的filter，因为不同版本的servlet会有一个问题，
 * 一次请求可能会调用多次过滤器。
 * 继承OncePerRequestFilter，保证一次请求只调用一次过滤器
*/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
	@Autowired
	private RedisCache redisCache;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		//获取token
		String token = request.getHeader("token");
		if (!StringUtils.hasText(token)){
			//放行
			filterChain.doFilter(request,response);
			return;
		}
		//解析token
		String userId;
		try {
			Claims claims = JwtUtil.parseJWT(token);
			userId = claims.getSubject();
		} catch (Exception e) {
			e.printStackTrace();
			//token过期或不存在
			throw new RuntimeException("token非法");
		}
		//从redis中获取用户信息
		String redisKey = "login:"+userId;
		LoginUserDetails loginUserDetails = redisCache.getCacheObject(redisKey);
		if (Objects.isNull(loginUserDetails)){
			throw new RuntimeException("用户未登录");
		}
		//存入SecurityContextHolder，供其他过滤器使用
		//获取权限信息封装到Authentication中
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(loginUserDetails, null,
						loginUserDetails.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		//放行
		filterChain.doFilter(request,response);
	}
}
