package elements;

import primitives.*;

public class SpotLight extends PointLight {

	private Vector direction;

	/**
	 * Getter for direction 
	 * @return direction
	 */
	public Vector getDirection() {
		return direction;
	}


	/**
	 * SpotLight constructor
	 * @param col
	 * @param position
	 * @param kC
	 * @param kL
	 * @param kQ
	 * @param direction
	 */
	public SpotLight(Color col, Point3D position, double kC, double kL, double kQ, Vector direction) {
		super(col, position, kC, kL, kQ);
		this.direction = new Vector(direction).normalization();
	}

	/**
	 * find the intensity of color in specific point
	 */
	@Override
	public Color getIntensity(Point3D p) {
		double ld = getL(p).dotProduct(direction);
		return ld > 0 ? super.getIntensity(p).scale(ld) : new Color();
	}

	/**
	 * find the direction of the source light
	 * @return direction
	 */
	@Override
	public Vector getD(Point3D p) {
		return direction;
	}

}
