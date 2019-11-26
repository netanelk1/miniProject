package elements;
import primitives.*;

public interface LightSource {
	/**
	 * find the intensity in specific point
	 * @param point
	 * @return color
	 */
	public Color getIntensity(Point3D point);
	
	/**
	 * find the vector L for specific point
	 * @param point
	 * @return vector
	 */
	public Vector getL(Point3D point);
	
	
	/**
	 * find the vector D (direction ) in specific point
	 * @param Point
	 * @returns vector
	 */
	public Vector getD(Point3D point);

}
