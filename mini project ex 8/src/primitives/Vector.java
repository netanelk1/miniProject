
package primitives;

public class Vector {
	private Point3D head;

	/**
	 * Vector constructor
	 * @param head
	 * 				the head of the vector
	 */
	public Vector(Point3D head) {
		if (Point3D.ZERO.equals(head))
			throw new IllegalArgumentException("Zero vector is not allowed");
		this.head = new Point3D(head);
	}

	/**
	 *  Vector constructor by double
	 *  @param x
	 *  @param y
	 *  @param z
	 * 
	 */
	public Vector(double x, double y, double z) {
		head = new Point3D(x, y, z);
		if (Point3D.ZERO.equals(head))
			throw new IllegalArgumentException("Zero vector is not allowed");
	}

	/**
	 * Vector copy constructor
	 * @param v
	 */
	public Vector(Vector v) {
		head = new Point3D(v.head);
	}

	/**
	 * Getter for head
	 * @return head
	 */
	public Point3D getHead() {
		return head;
	}

	
	/**
	 * add vector to vector
	 * @param vec
	 * @return the vector of the adding of two vectors
	 */
	public Vector add(Vector vec) // add vector
	{
		return new Vector((this.head).getX().add(vec.getHead().getX().getCoordinate()),
				(this.head).getY().add(vec.getHead().getY().getCoordinate()),
				(this.head).getZ().add(vec.getHead().getZ().getCoordinate()));
	}

	/**
	 * substract vector
	 * @param vec
	 * @return the subtraction between the two vectors(vec minus our vector)
	 */
	public Vector subtract(Vector vec) // substract vector
	{
		return new Vector((this.head).getX().substract(vec.getHead().getX().getCoordinate()),
				(this.head).getY().substract(vec.getHead().getY().getCoordinate()),
				(this.head).getZ().substract(vec.getHead().getZ().getCoordinate()));
	}

	/**
	 * scalar product function
	 * @param vec
	 * @return the scalar product between 2 vectors
	 */
	public double dotProduct(Vector vec) // dot product
	{
		return ((this.head.getX().getCoordinate()) * (vec.getHead().getX().getCoordinate())
				+ (this.head.getY().getCoordinate()) * (vec.getHead().getY().getCoordinate())
				+ (this.head.getZ().getCoordinate()) * (vec.getHead().getZ().getCoordinate()));
	}

	/**
	 * cross Product function
	 * @param vec
	 * @return the crossProduct between two vectors
	 */
	public Vector crossProduct(Vector vec) // cross product
	{
		return new Vector(
				(this.head.getY().getCoordinate()) * (vec.getHead().getZ().getCoordinate())
						- (this.head.getZ().getCoordinate()) * (vec.getHead().getY().getCoordinate()),
				((this.head.getZ().getCoordinate()) * (vec.getHead().getX().getCoordinate())
						- (this.head.getX().getCoordinate()) * (vec.getHead().getZ().getCoordinate())),
				((this.head.getX().getCoordinate()) * (vec.getHead().getY().getCoordinate())
						- (this.head.getY().getCoordinate()) * (vec.getHead().getX().getCoordinate())));
	}
	/**
	 * normalization of vector
	 * 
	 * @return the vector normalized
	 */
	public Vector normalization() // normalize
	{
		double num = lengthVec();
		head = new Point3D(this.head.getX().getCoordinate() / num, this.head.getY().getCoordinate() / num,
				this.head.getZ().getCoordinate() / num);
		return this;
	}

	/**
	 * scalar the vector
	 * @param num
	 * @return vector that is multiplied by scalar 
	 */
	public Vector scalar(double num) // mult vector in scalar
	{
		Vector v = new Vector((this.head.getX().getCoordinate()) * num, (this.head.getY().getCoordinate()) * num,
				(this.head.getZ().getCoordinate()) * num);
		return v;
	}

	/**
	 * 
	 * @return the length of the vector
	 */
	public double lengthVec() // length of vector
	{
		double num = Math.sqrt(head.getX().getCoordinate()*head.getX().getCoordinate() + head.getY().getCoordinate()*head.getY().getCoordinate()
		+head.getZ().getCoordinate()*head.getZ().getCoordinate());
		return num;
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
		if (getClass() != obj.getClass())
			return false;
		Vector other = (Vector) obj;
		if (head == null) {
			if (other.head != null)
				return false;
		} else if (!head.equals(other.head))
			return false;
		return true;
	}
	/**
	 * string
	 */
	@Override
	public String toString() {
		return "Vector :(" + this.head.getX().getCoordinate() + "," + this.head.getY().getCoordinate() + ","
				+ this.head.getZ().getCoordinate() + ")";
	}

}