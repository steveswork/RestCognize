package com.rest.cognizant.restful.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.services.ScaleShapeService;
import com.rest.cognizant.services.ShapeService;

@RestController
@RequestMapping( "/rest" )
public class RIndex {
	
	@RequestMapping( "/box/{length}/{width}/{hieght}" )
	public Box getShape( @PathVariable( value = "length" ) double length,
						 @PathVariable( value = "width" ) double width,
						 @PathVariable( value = "hieght" ) double height ){
		return boxService.get( length, width, height );
	} 

	@RequestMapping( "/box/{length}/{width}/{hieght}/{scalefactor}" )
	public Box getShape( @PathVariable( value = "length" ) double length,
						 @PathVariable( value = "width" ) double width,
						 @PathVariable( value = "hieght" ) double height,
						 @PathVariable( value = "scalefactor" ) double scaleFactor ){
		return scaleBoxService.scale( boxService.get( length, width, height ), scaleFactor );
	}
	
	@Autowired
	@Qualifier( "scaleBoxService" )
	public ScaleShapeService scaleBoxService;
	
	@Autowired
	@Qualifier( "getBoxService" )
	public ShapeService boxService;
}