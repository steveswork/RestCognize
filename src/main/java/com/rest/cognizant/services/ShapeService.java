package com.rest.cognizant.services;

import org.springframework.stereotype.Service;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.beans.Sphere;

@Service
public interface ShapeService {

	default Box get( double length, double width, double height ){
		return new Box();
	}

	default Sphere get( double radius, double length ){
		return new Sphere();
	}
	
}
