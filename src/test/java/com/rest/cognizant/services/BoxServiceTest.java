package com.rest.cognizant.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.cognizant.beans.Box;

@RunWith( SpringRunner.class )
@SpringBootTest
public class BoxServiceTest {
	
	@Test
	public void getABox() throws Exception{
		Assert.assertEquals( new Box( 4, 4, 4 ), boxService.get( 4, 4, 4 ));
	}
	
	@Autowired
	@Qualifier( "getBoxService" )
	private ShapeService boxService;
}