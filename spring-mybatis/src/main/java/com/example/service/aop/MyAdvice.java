package com.example.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author 张丽璇
 * @date 2023/2/20
 */
@Component
@Aspect
public class MyAdvice {
	@Pointcut("execution(* com.example.service.impl.UserServiceImpl.after())")
	private void pt(){}

	// @Before("pt()")
	public void before(){
		System.out.println("时间："+System.currentTimeMillis());
	}

	// @After("pt()")
	public void after(){
		System.out.println("时间："+System.currentTimeMillis());
	}

	@Around("pt()")
	public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		System.out.println("时间："+System.currentTimeMillis());
		Object result = proceedingJoinPoint.proceed();
		System.out.println("result="+result);
		System.out.println("时间："+System.currentTimeMillis());
		return result;
	}

	@AfterReturning("pt()")
	public void afterReturning(){
		System.out.println("afterReturning...");
	}

	@AfterThrowing("pt()")
	public void afterThrowing(){
		System.out.println("after throwing...");
	}
}
