package com.rest.cognizant.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Advices extends Pointcuts{
	
	@Around( "getDefaultFirstArgTypeWhenScaleFactorIsZero(scaleFactor)" )
	public Object zeroSecondArgument( ProceedingJoinPoint pjp, double scaleFactor ) throws Throwable{
		return scaleFactor == 0 ? pjp.getArgs()[ 0 ].getClass().newInstance() : pjp.proceed();
	}

}
