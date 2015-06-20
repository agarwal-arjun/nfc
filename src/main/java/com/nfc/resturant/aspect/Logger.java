package com.nfc.resturant.aspect;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
@Aspect
@Component
public class Logger {
	private final Log logger = LogFactory.getLog(this.getClass());
	
	@Around("execution(* com.nfc.resturant.*.*.*(..))")
	public Object aroundLoggingAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		logger.info("************Method Started************");
		logger.info("Method Name : " + joinPoint.getSignature().getName());
		logger.info("Method Arguments : " + Arrays.toString(joinPoint.getArgs()));
		
		Object obj=joinPoint.proceed();
		if(obj!=null)
		logger.info("Method Return  : " + obj.toString());
		
		logger.info("************Method End************");
		return obj;
	}
}
