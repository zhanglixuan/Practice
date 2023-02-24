package com.example.service.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author 张丽璇
 * @date 2023/2/21
 */
@Component
@Aspect
public class DataAdvice {
	@Pointcut("execution(* com.example.service.*.*(..))")
	private void servicePt(){}

	@Around("servicePt()")
	public void method(ProceedingJoinPoint pjt) throws Throwable {
		Signature signature = pjt.getSignature();
		String  className = signature.getDeclaringTypeName();
		String name = signature.getName();
		long startTime = System.currentTimeMillis();
		for (int i = 0; i < 10000; i++) {
			pjt.proceed();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("方法"+className+"."+name+"执行时间为："+(endTime-startTime));
	}
}
