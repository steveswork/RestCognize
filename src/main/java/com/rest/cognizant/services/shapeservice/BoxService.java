package com.rest.cognizant.services.shapeservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.services.ShapeService;

@Service
@Qualifier( "getBoxService" )
public class BoxService implements ShapeService {
	public Box get( double length, double width, double height ){
		return new Box( length, width, height );
	}
}