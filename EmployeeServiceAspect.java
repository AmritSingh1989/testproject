package com.javainuse.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class EmployeeServiceAspect {

	@AfterThrowing(value = "execution(* com.javainuse.service.EmployeeService.*(..)) ", throwing="e" )
	public void beforeAdvice(JoinPoint joinPoint,Exception e) {
		System.out.println("Throwing  method:" + joinPoint.getSignature());

		System.out.println("Creating Employee with name - " +e);
	}

	@After(value = "execution(* com.javainuse.service.EmployeeService.*(..)) and args(name,empId)")
	public void afterAdvice(JoinPoint joinPoint, String name, String empId) {
		System.out.println("After method:" + joinPoint.getSignature());

		System.out.println("Successfully created Employee with name - " + name + " and id - " + empId);
	}
}
