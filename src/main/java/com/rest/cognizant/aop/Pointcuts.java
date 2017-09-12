package com.rest.cognizant.aop;

import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
public class Pointcuts {

	@Pointcut( "execution(public * com.rest.cognizant.infrastructure.scaler.*.scale(*,double,..)) && args(*,scaleFactor,..)")
	public void getDefaultFirstArgTypeWhenScaleFactorIsZero( double scaleFactor ){}
}
