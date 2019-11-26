package geometries;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import primitives.*;

public class Sphere extends RadialGeometry {
	private Point3D o;

	/**
	 * 
	 * Contractor
	 */

	public Sphere(double radious, Point3D p, Color emission, Material material) {
		super(radious, emission, material);
		this.o = new Point3D(p);
	}

	/**
	 * Getters
	 */

	public Point3D getP() {
		return o;
	}

	/***
	 * 
	 * this function finds the intersection with the sphere
	 * @return Point3D 
	 */
	public Map<Geometry, List<Point3D>> findIntersections(Ray r) {
		Point3D p0 = r.getP00();
		Vector v = r.getDirection();
		Vector u;
		try {
			u = p0.subtract(o);
		} catch (Exception e) {
			// the ray starts in the center of the Sphere and it will cross the
			// sphere at and only
			// at the distance of radius from the ray beginning
			List<Point3D> intersectionsList = new ArrayList<Point3D>();
			Map<Geometry, List<Point3D>> intersections = new HashMap<Geometry, List<Point3D>>();
			intersectionsList.add(p0.add(v.scalar(radius)));
			intersections.put(this, intersectionsList);
			return intersections;
		}
		double tm = v.dotProduct(u);
		double ul = u.lengthVec();
		double d = Math.sqrt(ul * ul - tm * tm);
		if (d > radius) {
			return null;
		}
		double th = Math.sqrt(radius * radius - d * d);
		// Check whether it is tangent - in this case we will have only one
		// intersection point - according to tm
		if (Coordinate.isZero(th)) {
			if (tm > 0 && !Coordinate.isZero(tm)) {
				List<Point3D> intersectionsList = new ArrayList<Point3D>();
				Map<Geometry, List<Point3D>> intersections = new HashMap<Geometry, List<Point3D>>();
				intersectionsList.add(p0.add(v.scalar(tm)));
				intersections.put(this, intersectionsList);
				return intersections;
			}
			return null;
		}

		double t1 = tm + th;
		double t2 = tm - th;

		List<Point3D> intersectionsList = new ArrayList<Point3D>();
		if (t1 > 0 && !Coordinate.isZero(t1))
			intersectionsList.add(p0.add(v.scalar(t1)));
		if (t2 > 0 && !Coordinate.isZero(t2))
			intersectionsList.add(p0.add(v.scalar(t2)));

		// return list_intersection;
		if (intersectionsList.isEmpty())
			return null;

		Map<Geometry, List<Point3D>> intersections = new HashMap<Geometry, List<Point3D>>();
		intersections.put(this, intersectionsList);
		return intersections;
	}
	/**
	 * returns the normal
	 */
	public Vector getNormal(Point3D p) {
		return o.subtract(p).normalization();
	}
}