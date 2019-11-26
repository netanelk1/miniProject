package primitives;

public class Point2D {
	protected Coordinate x;
	protected Coordinate y;

	/**
	 * constructor of Point2D
	 * @param x2
	 * @param y2
	 */
	public Point2D(Coordinate x2, Coordinate y2) {
		this.x = new Coordinate(x2);
		this.y = new Coordinate(y2);
	}

	/**
	 * constructor by double
	 * @param x2
	 * @param y2
	 */
	public Point2D(double x2, double y2) {
		this.x = new Coordinate(x2);
		this.y = new Coordinate(y2);
	}

	/**
	 * Point2D copy constructor
	 * @param p
	 */
	public Point2D(Point2D p) {
		this.x = new Coordinate(p.x);
		this.y = new Coordinate(p.y);
	}

	/**
	 * Getter for coordinate x 
	 * @return x
	 */
	public Coordinate getX() {
		return x;
	}
	/**
	 * Getter for coordinate y 
	 * @return y
	 */
	public Coordinate getY() {
		return y;
	}

	/**
	 * check if they are equals
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point2D))
			return false;
		Point2D other = (Point2D) obj;
		return x.equals(other.x) && y.equals(other.y);
	}
	/**
	 * string
	 */
	@Override
	public String toString() {
		return "Point2D : x=" + x + ", y=" + y;
	}
}
