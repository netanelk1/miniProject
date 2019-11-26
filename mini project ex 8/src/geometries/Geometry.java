package geometries;
import java.util.List;
import java.util.Map;

import primitives.*;
public abstract class Geometry {
	
    protected Color emission;
	protected Material material;
    /**
     * Constructor of Geometry
     * @param emission
     * @param material
     */
	public Geometry(Color emission,Material material) {
		this.emission =emission;
		this.material=material;
	}
    /**
     * Constructor of Geometry
     * @param geo
     */
	public Geometry(Geometry geo)
	{
		this.emission=geo.emission;
		this.material=geo.material;
	}
	/**
	 * Getter
	 * @return color emission
	 */
	public Color getEmission() {
		return emission;
	}
	/**
	 * Getter
	 * @return material of the geometry
	 */

	public Material getMaterail(){
		return material;
	}

	/**
	 * find the normal vector in specific point
	 * @param p
	 * @return vector
	 */
	public abstract Vector getNormal(Point3D p);
	/**
	 * 
	 * @param y
	 * @return map
	 */
	public abstract Map<Geometry,List<Point3D>> findIntersections(Ray y);
}
 
 //322981275
//314623364