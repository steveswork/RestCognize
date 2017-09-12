package com.rest.cognizant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.rest.cognizant.RestCognizantApplication;

@SpringBootApplication
@EnableAspectJAutoProxy( proxyTargetClass = true )
public class RestCognizantApplication {
	public static void main( String[] args ) {
		SpringApplication.run( RestCognizantApplication.class, args );
	}
}
