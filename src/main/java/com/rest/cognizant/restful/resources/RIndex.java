package com.rest.cognizant.restful.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.beans.Sphere;
import com.rest.cognizant.services.ScaleShapeService;
import com.rest.cognizant.services.ShapeService;

@RestController
@RequestMapping( "/rest" )
public class RIndex {
	
	@RequestMapping( "/box/{length}/{width}/{height}" )
	public Box getBox( @PathVariable( value = "length" ) double length,
					   @PathVariable( value = "width" ) double width,
					   @PathVariable( value = "height" ) double height ){
		return boxService.get( length, width, height );
	} 

	@RequestMapping( "/box/{length}/{width}/{height}/{scalefactor}" )
	public Box getBox( @PathVariable( value = "length" ) double length,
					   @PathVariable( value = "width" ) double width,
					   @PathVariable( value = "height" ) double height,
					   @PathVariable( value = "scalefactor" ) double scaleFactor ){
		return scaleBoxService.scale( boxService.get( length, width, height ), scaleFactor );
	}
	
	@RequestMapping( "/sphere/{length}/{radius}" )
	public Sphere getSphere( @PathVariable( value = "length" ) double length,
						 	 @PathVariable( value = "radius" ) double radius ){
		return sphereService.get( radius, length  );
	} 

	@RequestMapping( "/sphere/{length}/{radius}/{scalefactor}" )
	public Sphere getSphere( @PathVariable( value = "length" ) double length,
							 @PathVariable( value = "radius" ) double radius,
							 @PathVariable( value = "scalefactor" ) double scaleFactor ){
		return scaleSphereService.scale( sphereService.get( radius, length  ), scaleFactor );
	}
	
	@Autowired
	@Qualifier( "scaleBoxService" )
	private ScaleShapeService scaleBoxService;
	
	@Autowired
	@Qualifier( "scaleSphereService" )
	private ScaleShapeService scaleSphereService;
	
	@Autowired
	@Qualifier( "getBoxService" )
	private ShapeService boxService;
	
	@Autowired
	@Qualifier( "getSphereService" )
	private ShapeService sphereService;
}