package geometries;

import primitives.*;
public abstract class RadialGeometry extends Geometry {
	protected double radius;
	
	
	

	/**
	 * RadialGeometry constructor
	 *  @param radious
	 *   @param emission
	 *   @param material
	 */
	public RadialGeometry(double radious,Color emission,Material material) {
		super(emission,material);
		this.radius = radious;
	}
	
	public double getRadious() {
		return this.radius;
	}
	
}

