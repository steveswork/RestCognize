package com.rest.cognizant.services;

import org.springframework.stereotype.Service;

import com.rest.cognizant.beans.Box;

@Service
public interface ScaleShapeService{
	/**
	 * triggers compute method of the Shapes concrete type
	 * @param box
	 * @param scaleFactor
	 */
	public default Box scale( Box box, double scaleFactor ){
		return box;
	}	
}