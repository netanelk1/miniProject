package primitives;

public class Ray {
	private Point3D p00;
	private Vector direction;

	/**
	 * Ray constructor
	 * 
	 * @param p00
	 *            the ray begin point
	 * @param direction
	 *            the ray direction
	 */
	public Ray(Point3D p00, Vector direction) { // constructor
		this.p00 = new Point3D(p00);
		this.direction = new Vector(direction).normalization();
	}
	/**
	 *Ray copy constructor
	 * @param r
	 */
	public Ray(Ray r) {
		this.p00 = new Point3D(r.getP00());
		this.direction =new Vector(r.getDirection());
	}

	/**
	 * Getter for Ray P0
	 * @return p00
	 */
	
	public Point3D getP00() {
		return p00;
	}

	/**
	 * Getter for Ray direction
	 * 
	 * @return direction
	 */
	public Vector getDirection() {
		return direction;
	}
	/**
	 * check if they are equals
	 * @return true if they equal and false if the are not equal
	 */
	@Override
	public boolean equals(Object obj) { // equals function
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ray other = (Ray) obj;
		if (direction == null) {
			if (other.direction != null)
				return false;
		} else if (!direction.equals(other.direction))
			return false;
		if (p00 == null) {
			if (other.p00 != null)
				return false;
		} else if (!p00.equals(other.p00))
			return false;
		return true;
	}
	/**
	 * string
	 */
	@Override
	public String toString() {
		return "Ray: " + "p00: (" + p00.getX().getCoordinate() + "," + p00.getY().getCoordinate() + ","
				+ p00.getZ().getCoordinate() + ")" + "direction: (" + direction.getHead().getX().getCoordinate() + ","
				+ direction.getHead().getY().getCoordinate() + "," + direction.getHead().getZ().getCoordinate() + ")";
	}

}
