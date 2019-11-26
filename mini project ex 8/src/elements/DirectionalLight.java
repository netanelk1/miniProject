package elements;

import primitives.*;

public class DirectionalLight extends Light implements LightSource {

	private Vector direction;

	/**
	 * DirectionalLight constructor
	 *  @param color
	 *   @param dir
	 */
	

	public DirectionalLight(Color color, Vector dir) {
		super(color);
		direction = new Vector(dir).normalization();
	}

	/**
	 * Getter for direction
	 * @return direction
	 */

	public Vector getDirection() {
		return direction;
	}

	
	/**
	 * find the direction of the light
	 * @return direction
	 */
	@Override
	public Vector getD(Point3D P) {
		return direction;
	}

	/**
	 * find the vector L 
	 *  @param direction
	 */
	@Override
	public Vector getL(Point3D P) {
		return direction;
	}
	
	/**
	 * find the intensity of the light in some point
	 * @return getIntensity()
	 */
	@Override
	public Color getIntensity(Point3D P) {
		return getIntensity();
	}
}
