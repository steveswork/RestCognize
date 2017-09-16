package com.rest.cognizant.infrastructure;

import org.springframework.stereotype.Component;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.beans.Data;
import com.rest.cognizant.beans.Shapes;
import com.rest.cognizant.beans.Sphere;

@Component
public interface Scaler{
	/**
	 * scales all affected fields of the Data concrete type by a factor
	 * @param data
	 * @param scaleFactor
	 * @return
	 */
	public default Data scale( Data data, double scaleFactor ){
		return null;
	}	
	
	/**
	 * scales all affected fields of the Shapes concrete type by a factor
	 * @param data
	 * @param scaleFactor
	 * @return
	 */
	public default Shapes scale( Shapes shape, double scaleFactor ){
		return null;
	}
	
	/**
	 * scales all affected fields of type Box by a factor
	 * @param box
	 * @param scaleFactor
	 * @return
	 */
	public default Box scale( Box box, double scaleFactor){
		return new Box();
	}
	
	/**
	 * scales all affected fields of type Box by a factor
	 * @param sphere
	 * @param scaleFactor
	 * @return
	 */
	public default Sphere scale( Sphere sphere, double scaleFactor){
		return new Sphere();
	}
}
