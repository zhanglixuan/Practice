package com.example.exception;

import com.example.result.Result;
import org.apache.shiro.ShiroException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 张丽璇
 * @date 2023/3/7
 * 全局异常处理器（springmvc特有的，捕获表现层抛出的异常，AOP实现，为controller增强功能）
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {
	@ExceptionHandler(AuthenticationException.class)
	public Result doRuntimeException(Exception e){
		return new Result(401,null, e.getMessage());
	}
}
