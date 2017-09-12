package com.rest.cognizant.beans;

import org.springframework.stereotype.Component;

@Component
public interface Shapes extends Data {
	public double getArea();
	public double getPerimeter();
	public double getVolume();
	public void computeArea();
	public void computePerimeter();
	public void computeVolume();
}
