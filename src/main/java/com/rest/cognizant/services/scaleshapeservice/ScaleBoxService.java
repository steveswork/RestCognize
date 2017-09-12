package com.rest.cognizant.services.scaleshapeservice;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.infrastructure.Scaler;
import com.rest.cognizant.services.ScaleShapeService;

@Service
@Qualifier( "scaleBoxService" )
public class ScaleBoxService implements ScaleShapeService {
	
	@Override
	public Box scale( Box box, double scaleFactor ) {
		this.scaleFactor = scaleFactor;
		return fetchScaler( scaleFactor ).scale( box, scaleFactor );
	}
	
	public Scaler fetchScaler( double scaleFactor ){
		return scaleFactor > 0 ? incScaler : decScaler;
	}
	
	public double getCurrentScaleFactor(){
		return scaleFactor;
	}
	
	public ScaleBoxService(){
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