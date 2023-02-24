package com.example.controller;

import com.example.exception.BusinessException;
import com.example.exception.SystemException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 */
@RestControllerAdvice //声明为异常处理类
public class ProjectExceptionAdvice {
	@ExceptionHandler(BusinessException.class)
	public Result doBusinessException(BusinessException ex){
		System.out.println("do businessException...");
		return new Result(ex.getCode(), ex.getMessage());
	}

	@ExceptionHandler(SystemException.class)
	public Result doSystemException(SystemException ex){
		//记录日志（错误堆栈）
		//发送邮件给开发人员
		//发送短信给运维人员
		System.out.println("do SystemException...");
		return new Result(ex.getCode(),ex.getMessage());
	}

	@ExceptionHandler(Exception.class) //异常处理标记，处理何种异常
	public Result doException(Exception ex){//接收异常对象
		//记录日志（错误堆栈）
		//发送邮件给开发人员
		//发送短信给运维人员
		System.out.println("do Exception...");
		return new Result(Code.SYSTEM_UNKNOWN_ERR,null,"系统繁忙，请联系管理员！");
	}
}
