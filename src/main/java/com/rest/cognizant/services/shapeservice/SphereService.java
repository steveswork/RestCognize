package com.rest.cognizant.services.shapeservice;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.rest.cognizant.beans.Sphere;
import com.rest.cognizant.services.ShapeService;

@Service
@Qualifier( "getSphereService" )
public class SphereService implements ShapeService {
	public Sphere get( double radius, double length  ){
		return new Sphere( radius, length  );
	}
}