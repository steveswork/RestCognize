package com.rest.cognizant.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.cognizant.beans.Sphere;

@RunWith( SpringRunner.class )
@SpringBootTest
public class SphereServiceTest {
	
	@Test
	public void getABox() throws Exception{
		Assert.assertEquals( new Sphere( 4, 4 ), sphereService.get( 4, 4 ));
	}
	
	@Autowired
	@Qualifier( "getSphereService" )
	private ShapeService sphereService;
}
