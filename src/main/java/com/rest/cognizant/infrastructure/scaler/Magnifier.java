package com.rest.cognizant.infrastructure.scaler;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.beans.Sphere;
import com.rest.cognizant.infrastructure.Scaler;

@Component
@Qualifier( "incScale" )
public class Magnifier implements Scaler {
	@Override
	public Box scale( Box box, double scaleFactor) {
		scaleFactor = Math.abs( scaleFactor );
		return new Box( box.getLength() * scaleFactor, 
					    box.getWidth() * scaleFactor,
					    box.getHeight() * scaleFactor );
	} 
	@Override
	public Sphere scale( Sphere sphere, double scaleFactor) {
		scaleFactor = Math.abs( scaleFactor );
		return new Sphere( sphere.getRadius() * scaleFactor, 
						   sphere.getLength() * scaleFactor );
	} 
}
