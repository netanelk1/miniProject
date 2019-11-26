package geometries;
import primitives.*;

public class GeoPoint {
	public Geometry geometry;
	public Point3D point;
	
	
	/**
	 * Getter 
	 * @return geometry
	 */
	public Geometry getGeometry() {
		return geometry;
	}
	
	/**
	 * Getter
	 * @return point
	 */
	public Point3D getPoint() {
		return point;
	}

    /**
     * Contractor
     * @param geometry
     * @param point
     */
	public GeoPoint(Geometry geometry, Point3D point) {
		
		this.geometry = geometry;
		this.point = point;
	}
	
	/**
	 * Contractor
	 * @param p
	 */
	public GeoPoint(GeoPoint p)
	{
		this.geometry=p.geometry;
		this.point=p.point;
	}
	
	
	
	
}
