package com.rest.cognizant.services;

import org.springframework.stereotype.Service;

import com.rest.cognizant.beans.Box;

@Service
public interface ShapeService {

	default Box get( double length, double width, double height ){
		return new Box();
	}
	
}
