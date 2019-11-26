
package geometries;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import primitives.*;

public class Plane extends Geometry {
	protected Point3D q;
	protected Vector normal;

	
	/**
	 * Plane constructor
	 * @param p
	 * @param v
	 * @param emision
	 * @param material
	 */
	public Plane(Point3D p, Vector v, Color emision, Material material) {
		super(emision, material);
		this.q = new Point3D(p);
		this.normal = new Vector(v).normalization();
	}
	/**
	 * plane constructor
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param emision
	 * @param material
	 * 
	 */

	public Plane(Point3D p1, Point3D p2, Point3D p3, Color emision, Material material) {
		super(emision, material);
		Vector vA = p2.subtract(p1);
		Vector vB = p2.subtract(p3);
		this.normal = vA.crossProduct(vB).normalization();
		this.q = new Point3D(p1);
	}

	/***
	 * Getter
	 * @return point
	 */
	public Point3D getP1() {
		return q;
	}
    /**
     * Contractor
     * @return normal vector
     */
	public Vector getV() {
		return normal;
	}

	/**
	 * finding the intersections of the ray in the plane
	 * @param r
	 * @return intersections
	 */
	public Map<Geometry, List<Point3D>> findIntersections(Ray r) {
		Point3D p0 = r.getP00();
		Vector v = r.getDirection();
		Vector vec1 = p0.subtract((q));
		double num2 = normal.dotProduct(v);
		if (Coordinate.isZero(num2))
			return null;
		double num1 = normal.dotProduct(vec1);
		double t = num1 / num2;
		Coordinate x=new Coordinate(t);
		if (x.getCoordinate()<=0)
			return null;
		
		List<Point3D> listIntersection = new ArrayList<Point3D>();
		listIntersection.add(p0.add(v.scalar(x.getCoordinate())));
		Map<Geometry, List<Point3D>> intersections = new HashMap<Geometry, List<Point3D>>();
		// Put list_intersection into intersections with geometry (this) as key
		intersections.put(this, listIntersection);
		return intersections;
	}

	/**
	 * finding the normal to the plane in this point
	 * @param p
	 * @return normal
	 */
	public Vector getNormal(Point3D p) {
		return normal;
	}
}