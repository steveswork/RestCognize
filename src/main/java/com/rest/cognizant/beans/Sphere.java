package com.rest.cognizant.beans;

import org.springframework.stereotype.Component;

@Component
public class Sphere implements Shapes {
	public Sphere(){
		this( 0, 0 );
	}
	public Sphere( double radius ){
		this( radius, 1 );
	}
	public Sphere( double radius, double length ){
		this.length = length;
		this.radius = radius;
		compute();
	}
	public double getLength(){
		return length;
	}
	public double getRadius() {
		return radius;
	}
	@Override
	public double getArea() {
		return area;
	}
	@Override
	public double getPerimeter() {
		return perimeter;
	}
	@Override
	public double getVolume() {
		return volume;
	}
	@Override
	public void computeArea() {
		area = Math.PI * Math.pow( radius, 2 );
	}
	@Override
	public void computePerimeter() {
		perimeter = 2 * Math.PI * radius;
	}
	@Override
	public void computeVolume() {
		volume = Math.PI * ( 4 / 3 ) * Math.pow( radius,  3 );
	}
	private void compute(){
		computeArea();
		computePerimeter();
		computeVolume();
	}
	private double area, length, radius, perimeter, volume;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(area);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(perimeter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(radius);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	@Override
	public boolean equals( Object obj ){
		if( this == obj )
			return true;
		if( obj == null )
			return false;
		if( getClass() != obj.getClass() )
			return false;
		Sphere other = ( Sphere ) obj;
		if( area != other.getArea() )
			return false;
		if( length != other.getLength() )
			return false;
		if( perimeter != other.getPerimeter() )
			return false;
		if( volume != other.getVolume() )
			return false;
		if( radius != other.getRadius() )
			return false;
		return true;
	}
}