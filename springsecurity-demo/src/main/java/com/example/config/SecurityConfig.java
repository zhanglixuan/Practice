package com.example.config;

import com.example.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author 张丽璇
 * @date 2023/3/5
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
	@Autowired
	AuthenticationEntryPoint authenticationEntryPoint;
	@Autowired
	AccessDeniedHandler accessDeniedHandler;

	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/**
	 * 认证相关的配置都是在这个config中重写configure()方法
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//如果不关闭，除了校验自己的token还有去校验csrf_token，请求没携带则不通过，所以必须关闭
		http.csrf().disable()  //关闭csrf（前后端分离需要把这个功能关闭掉）
				//不通过session获取SecurityContext
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
				.authorizeRequests()
				//对登录接口 允许匿名访问（没登录的情况下能访问这个接口，登录之后则不能访问）
				.antMatchers("/user/login").anonymous()
				//除上面外的所有请求全部需要鉴权认证
				.anyRequest().authenticated();

		//添加到SpringSecurity过滤器链中，并指定过滤器顺序
		http.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
		//配置异常处理器
		http.exceptionHandling()
				//配置认证失败处理器
				.authenticationEntryPoint(authenticationEntryPoint)
				//配置授权失败处理器
				.accessDeniedHandler(accessDeniedHandler);
		//允许跨越
		http.cors();
	}

	@Bean
	@Override
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
}
