package com.filipan.service.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class ArticlePointcuts {

	@Pointcut("execution(* com.filipan.service.ArticleService.create(..))")
	public void notifyOperation(){}
	
}
