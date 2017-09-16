package com.rest.cognizant.services;

import org.springframework.stereotype.Service;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.beans.Sphere;

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

	/**
	 * triggers compute method of the Shapes concrete type
	 * @param sphere
	 * @param scaleFactor
	 */
	public default Sphere scale( Sphere sphere, double scaleFactor ){
		return sphere;
	}	
}