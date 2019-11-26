package geometries;

import java.util.List;
import java.util.Map;

import primitives.*;

public class Triangle extends Plane {
	private Point3D p2;
	private Point3D p3;

	/**
	 * Getter
	 * @return p2
	 */
	public Point3D getP2() {
		return p2;
	}

	/**
	 * Getter
	 * @return p3
	 */
	public Point3D getP3() {
		return p3;
	}

	

	/**
	 * Triangle constructor
	 * @param p1
	 * @param p2
	 * @param p3
	 * @param v
	 *  @param emision
	 *   @param material 
	 */
	
	public Triangle(Point3D p1, Point3D p2, Point3D p3, Vector v, Color emision, Material material) {
		super(p1, v, emision, material);
		this.p2 =new Point3D(p2);
		this.p3 =new Point3D(p3);
	}

	/**
	 * Triangle constructor
	 *  @param p1
	 *  @param p2
	 *  @param p3
	 *  @param emision
	 *  @param material
	 */
	
	public Triangle(Point3D p1, Point3D p2, Point3D p3, Color emision, Material material) {
		super(p1, p2, p3, emision, material);
		this.p2 =new Point3D(p2);
		this.p3 =new Point3D(p3);
	}// 3 points

	/**
	 * finding the intersections of the ray in the triangle
	 *  @param r
	 *  @return intersections
	 */
	public Map<Geometry, List<Point3D>> findIntersections(Ray r) {
		Map<Geometry, List<Point3D>> intersections = super.findIntersections(r);
		if (intersections == null)
			return null;

		Point3D p0 = new Point3D(r.getP00());
		Vector v1, v2, v3;
		try {
			v1 = p0.subtract(this.q);
			v2 = p0.subtract(this.p2);
			v3 = p0.subtract(this.p3);
		} catch (Exception e) {
			return null;
		}
		Vector n1 = v1.crossProduct(v2);
		Vector n2 = v2.crossProduct(v3);
		Vector n3 = v3.crossProduct(v1);

		Point3D point = intersections.entrySet().iterator().next().getValue().get(0);
		Vector v = p0.subtract(point);

		double s1 = v.dotProduct(n1);
		double s2 = v.dotProduct(n2);
		double s3 = v.dotProduct(n3);
		if ((s1 > 0 && s2 > 0 && s3 > 0) || (s1 < 0 && s2 < 0 && s3 < 0))
			return intersections;
		else
			return null;
	}
}