package com.rest.cognizant.services.scaleshapeservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rest.cognizant.beans.Sphere;
import com.rest.cognizant.infrastructure.Scaler;
import com.rest.cognizant.services.ScaleShapeService;

@Service
@Qualifier( "scaleSphereService" )
public class ScaleSphereService implements ScaleShapeService {
	
	@Override
	public Sphere scale( Sphere sphere, double scaleFactor ) {
		this.scaleFactor = scaleFactor;
		return fetchScaler( scaleFactor ).scale( sphere, scaleFactor );
	}
	
	public Scaler fetchScaler( double scaleFactor ){
		return scaleFactor > 0 ? incScaler : decScaler;
	}
	
	public double getCurrentScaleFactor(){
		return scaleFactor;
	}
	
	public ScaleSphereService(){
		scaleFactor = 1D;
	}
	
	private double scaleFactor;
	
	@Autowired
	@Qualifier( "decScale" )
	private Scaler decScaler;
	
	@Autowired
	@Qualifier( "incScale" )
	private Scaler incScaler;
	
}
