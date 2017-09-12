package com.rest.cognizant.infrastructure.scaler;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.infrastructure.Scaler;

@RunWith( SpringRunner.class )
@SpringBootTest
public class MagnifierTest {
	
	@Test
	public void MultiplyBoxDimensionsby10(){
		Assert.assertEquals( new Box( 100, 100, 100 ), magnifier.scale( new Box( 10, 10, 10 ), 10 ));
	}
	
	@Test
	public void MultiplyBoxDimensionsbyNegative10(){
		Assert.assertEquals( new Box( 100, 100, 100 ), magnifier.scale( new Box( 10, 10, 10 ), -10 ));
	}
	
	@Test
	public void ResetsToDefaultBoxDimensions(){
		Assert.assertEquals( new Box(), magnifier.scale( new Box( 10, 10, 10 ), 0 ));
	}
	
	@Autowired
	@Qualifier( "incScale" )
	private Scaler magnifier;

}
