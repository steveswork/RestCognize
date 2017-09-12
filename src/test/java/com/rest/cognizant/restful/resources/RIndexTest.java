package com.rest.cognizant.restful.resources;

import javax.ws.rs.core.MediaType;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.rest.cognizant.beans.Box;
import com.rest.cognizant.services.scaleshapeservice.ScaleBoxService;
import com.rest.cognizant.services.shapeservice.BoxService;

@RunWith( SpringRunner.class )
@WebMvcTest( RIndex.class)
public class RIndexTest {
 
	@Test
	public void requestABox() throws Exception {
	    
		Box testBox = new Box( 8, 8, 8);
		Mockito.when( boxService.get( 8, 8, 8 )).thenReturn( testBox );
		Mockito.when( scaleBoxService.scale( testBox, 0 )).thenReturn( new Box() );
		
		mvc.perform( MockMvcRequestBuilders.get( "/rest/box/8/8/8/0" )
	      .contentType( MediaType.APPLICATION_JSON ))
	      .andExpect( MockMvcResultMatchers.status().isOk() );
	}
	
    @Autowired
    private MockMvc mvc;
    
    @MockBean
	public ScaleBoxService scaleBoxService;
	
    @MockBean
	public BoxService boxService;

}
