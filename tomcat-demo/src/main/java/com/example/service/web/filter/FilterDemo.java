package com.example.service.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
//配置拦截资源的路径
@WebFilter("/*")//拦截所有
public class FilterDemo implements Filter {
	//必须复写接口中所有的方法，可以空实现，否则运行报错
	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("FilterDemo...");
		//放行
		filterChain.doFilter(servletRequest,servletResponse);
	}

	public void destroy() {

	}
}
