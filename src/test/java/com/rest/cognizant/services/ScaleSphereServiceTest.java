package com.rest.cognizant.services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.rest.cognizant.beans.Sphere;
import com.rest.cognizant.infrastructure.Scaler;

@RunWith( SpringRunner.class )
@SpringBootTest
public class ScaleSphereServiceTest {

	@Test
	public void circumventScaleInvocationAndReturnDefaultBoxDimensionsForZeroScaleFactor(){
		Mockito.verify( decScaler , Mockito.never() ).scale( sphere, 0 );	
	}
	
	@Test
	public void doNotInvokeTheDecScalerForZeroScaleFactor(){
		scaleSphereService.scale( sphere, 0 );
		Mockito.verifyZeroInteractions( incScaler );	
	}
	
	@Test
	public void invokeIncScalerForPositiveScaleFactor(){
		scaleSphereService.scale( sphere, 1 );
		Mockito.verify( incScaler, Mockito.times( 1 )).scale( sphere, 1 );
	}
	
	@Test
	public void doNotInvokeTheDecScalerForPositiveScaleFactor(){
		scaleSphereService.scale( sphere, 1 );
		Mockito.verifyZeroInteractions( decScaler );	
	}
	
	@Test
	public void invokeDecScalerForNegativeScaleFactor(){
		scaleSphereService.scale( sphere, -1 );
		Mockito.verify( decScaler, Mockito.times( 1 )).scale( sphere, -1 );
	}
	
	@Test
	public void doNotInvokeTheIncScalerForNegativeScaleFactor(){
		scaleSphereService.scale( sphere, -1 );
		Mockito.verifyZeroInteractions( incScaler );	
	}
    
    public ScaleSphereServiceTest(){
    	sphere = new Sphere( 8, 8 );
    }
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks( this );
	}
    
	
	@Autowired
	@Qualifier( "scaleSphereService" )
	@InjectMocks
	private ScaleShapeService scaleSphereService;
	
	@Mock
	private Scaler decScaler;
	
	@Mock
	private Scaler incScaler;

	private Sphere sphere;
}
