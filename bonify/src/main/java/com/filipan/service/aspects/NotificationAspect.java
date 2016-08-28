package com.filipan.service.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.filipan.model.Article;

@Component
@Aspect
public class NotificationAspect {

	@AfterReturning(pointcut = "ArticlePointcuts.notifyOperation()", returning = "result")
	public void notify(final JoinPoint joinPoint, final Article result) {

		// Here we can simulate sending a mail or a push notification that a new
		// article has arrived
		System.out.println("New article: " + result);

	}

}
