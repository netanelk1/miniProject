package geometries;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import primitives.*;

/**
 * The composite object representing composite body built from several
 * geometries
 * 
 * @author Netanel & Ynon
 *
 */
public class Geometries extends Geometry {
	private List<Geometry> itsGeometries = new ArrayList<Geometry>();

	/***
	 * 
	 *  constructor
	 */
	public Geometries() {
		super(null, null);
	}

	/***
	 * 
	 * 
	 * adds another  geometry to the list
	 * @param g
	 */
	public void add(Geometry g) {
		itsGeometries.add(g);
	}
    /**
     * returns the normal
     */
	public Vector getNormal(Point3D point){
		return null;
	}
    /**
     * find intersection
     */
	public Map<Geometry, List<Point3D>> findIntersections(Ray R) {
		Map<Geometry, List<Point3D>> intersectionPoints = null;

		for (Geometry g : itsGeometries) {
			Map<Geometry, List<Point3D>> geometryIntersectionPoints = g.findIntersections(R);
			if (geometryIntersectionPoints != null) {
				if (intersectionPoints == null)
					intersectionPoints = new HashMap<Geometry, List<Point3D>>();
				intersectionPoints.putAll(g.findIntersections(R));
			}
		}
		
		return intersectionPoints;
	}
}
