package com.example.handler;

import com.alibaba.fastjson.JSON;
import com.example.utils.ResponseResult;
import com.example.utils.WebUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 张丽璇
 * @date 2023/3/6
 * 权限（授权）失败处理器
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
	@Override
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
		ResponseResult result = new ResponseResult(HttpStatus.FORBIDDEN.value(), "您的权限不足");
		String s = JSON.toJSONString(result);
		WebUtils.renderString(response,s);
	}
}
