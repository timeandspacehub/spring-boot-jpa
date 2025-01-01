package com.example.demo.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AspectConfig {
	
	//Calling getClass() method returns class where it is called. 
	Logger logger = LoggerFactory.getLogger(getClass());
	
	//First .* means for all the classes inside the package. 
	//Second .* means for all the methods inside that class.
	//(..) means the method can have any no. of method parameters 1, 2, 3, ...
	//So, @Before annotation is "Advice" i.e. when to intercept 
	//AND the value inside double quotes "Point Cut" i.e. which methods to intercept
	// Advice + Point Cut = Aspect
	//Execution instance that will come from controller is Joint Point
	/**
	@Before(value = "execution(* com.example.demo.controller.*.*(..))")
	public void beforeAdvice(JoinPoint joinpoint) {
		logger.info("Inside Before Advice");
		
	}
	**/
	
	//Intercept all the methods that have exactly one method parameter.
	//Here we have changed the pointcut - i.e. which method to intercept
	//The value passed into the method parameter will now be assigned to "object" defined below. 
	//You can access the object by declaring object. Parameter names MUST match! 
	@Before(value = "execution(* com.example.demo.controller.*.*(..)) and args(object)")
	public void beforeAdvice(JoinPoint joinpoint, Object object) {
		logger.info("Request= " + object);
		
	}
	
	
}