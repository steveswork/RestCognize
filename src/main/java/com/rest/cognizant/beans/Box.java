package com.rest.cognizant.beans;

import org.springframework.stereotype.Component;

@Component
public class Box implements Shapes {
	public Box(){
		this( 0, 0, 0 );
	}
	public Box( double length ){
		this( length, 1, 1 );
	}
	public Box( double length, double width ){
		this( length, width, 1 );
	}
	public Box( double length, double width, double height ){
		this.length = length;
		this.width = width;
		this.height = height;
		compute();
	}
	public double getLength(){
		return length;
	}
	public double getWidth() {
		return width;
	}
	public double getHeight() {
		return height;
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
		area = length * width;
	}
	@Override
	public void computePerimeter() {
		perimeter = 2 * ( length + width );
	}
	@Override
	public void computeVolume() {
		volume = height * length * width;
	}
	private void compute(){
		computeArea();
		computePerimeter();
		computeVolume();
	}
	private double area, length, height, perimeter, volume, width;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(area);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(height);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(length);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(perimeter);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(volume);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(width);
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
		Box other = ( Box ) obj;
		if( area != other.getArea() )
			return false;
		if( height != other.getHeight() )
			return false;
		if( length != other.getLength() )
			return false;
		if( perimeter != other.getPerimeter() )
			return false;
		if( volume != other.getVolume() )
			return false;
		if( width != other.getWidth() )
			return false;
		return true;
	}
}