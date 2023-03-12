package com.example.filter;

import com.alibaba.fastjson.JSON;
import com.example.result.CodeUtil;
import com.example.result.Result;
import com.example.shiro.TokenWebSessionManager;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 张丽璇
 * @date 2023/3/7
 * 自定义过滤器继承自shiro的UserFilter处理token鉴权异常问题
 */
@Component
public class SessionCheckFilter extends UserFilter {

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		// HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//解决跨域问题
		// if ("OPTIONS".equals(httpRequest.getMethod())){
		// 	httpResponse.setStatus(HttpServletResponse.SC_NO_CONTENT);
		// 	System.out.println("跨域....");
		// 	return true;
		// }
		httpResponse.setStatus(200);
		httpResponse.setContentType("application/json");
		httpResponse.setCharacterEncoding("UTF-8");
		Result result = new Result(CodeUtil.UN_AUTHENTICATION, null,"token非法");
		String s = JSON.toJSONString(result);
		httpResponse.getWriter().print(s); //写入响应体中
		httpResponse.getWriter().flush();
		httpResponse.getWriter().close();
		return false;
	}
}
