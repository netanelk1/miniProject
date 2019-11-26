package primitives;

public class Point3D extends Point2D {
	private Coordinate z;
	public static Point3D ZERO = new Point3D(0.0, 0.0, 0.0);

	/**
	 * Point3D constructor by coordinate
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(Coordinate x, Coordinate y, Coordinate z) 
	{
		super(x, y);
		this.z = new Coordinate(z);

	}
	/**
	 * Point3D constructor by double
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(double x, double y, double z) 
	{
		super(x, y);
		this.z = new Coordinate(z);
	}

	/**
	 * Point3D copy constructor
	 * @param p
	 */
	public Point3D(Point3D p) {
		super(p);
		z = new Coordinate(p.z);
	}

	/**
	 * Getter for z coordinate
	 * 
	 * @return z
	 */
	public Coordinate getZ() {
		return z;
	}
	/**
	 * Subtracts this point from point2, i.e. produces vector from this point to point2
	 * @param point2 point to subtract from
	 * @return vector from this point to point2
	 */
	public Vector subtract(Point3D point2) // vector by two points
	{
		double Dx = (point2.getX()).getCoordinate() - this.x.getCoordinate();
		double Dy = (point2.getY()).getCoordinate() - this.y.getCoordinate();
		double Dz = (point2.getZ()).getCoordinate() - this.z.getCoordinate();
		return new Vector(Dx, Dy, Dz);
	}

	/**
	 * add vector to point 
	 * @param vec
	 * @return point of the adding
	 */
	public Point3D add(Vector vec) // add vector to point
	{
		return new Point3D((this.x).add(vec.getHead().getX().getCoordinate()),
				(this.y).add(vec.getHead().getY().getCoordinate()), (this.z).add(vec.getHead().getZ().getCoordinate()));
	}

	/**
	 * function of distance
	 * @param point2
	 * @return the distance between the two points
	 */
	public double distance(Point3D point2) // distance between two points
	{
		return (Math.pow(point2.getX().getCoordinate() - (this.x).getCoordinate(), 2)
				+ Math.pow(point2.getY().getCoordinate() - (this.y).getCoordinate(), 2)
				+ Math.pow(point2.getZ().getCoordinate() - (this.z).getCoordinate(), 2));
	}
	/**
	 * check if they are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point3D other = (Point3D) obj;
		return super.equals(obj) && z.equals(other.z);
	}
	/**
	 * string
	 */
	@Override
	public String toString() {
		return "Point3D : x=" + x + ", y=" + y + " ,z=" + z;
	}
}
