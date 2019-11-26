package primitives;

public class Coordinate {
	private double coordinate;

	private static final int ACCURACY = -20;
	//public static Coordinate ZERO = new Coordinate(0);
	/**
	 * Getters
	 * @return  exp 
	 */
	private static int getExp(double num) {
		return (int) ((Double.doubleToRawLongBits(num) >> 52) & 0x7FFL) - 1023;
	}
	/**
	 * check if the coordinate is zero 
	 * @param n
	 * @return true of false 	
	 */
	public static boolean isZero(double n) {
		return getExp(n) < ACCURACY;
	}
	
	
	/**
	 * constructor 
	 * @param coord
	 */
	public Coordinate(double coord) {
		this.coordinate = (isZero(coord)) ? 0.0 : coord;
	}
	/**
	 * constructor 
	 */
	public Coordinate(Coordinate other) { 
		this.coordinate = other.getCoordinate();
	}
	

	/***
	 * Getters
	 * @return coordinate
	 */

	public double getCoordinate() {
		return coordinate;
	}
	

	/**
	 * adds two coordinates
	 * @param other
	 * @return coordinate
	 */

	public double add(double other) 
	{
		int otherExp = getExp(coordinate);
		int thisExp = getExp(coordinate);
		if (otherExp - thisExp < ACCURACY)
			return coordinate;
		if (thisExp - otherExp < ACCURACY)
			return other;
		double result = coordinate + other;
		int resultExp = getExp(result);
		return resultExp - thisExp < ACCURACY ? 0.0 : result;
	}
	/**
	 * Subtract the two coordinates 
	 * @param other
	 * @return coordinate
	 */
	public double substract(double other) 
	{
		int otherExp = getExp(other);
		int thisExp = getExp(coordinate);
		if (otherExp - thisExp < ACCURACY)
			return coordinate;
		if (thisExp - otherExp < ACCURACY)
			return other;
		double result = coordinate - other;
		int resultExp = getExp(result);
		return resultExp - thisExp < ACCURACY ? 0.0 : result;
	}
	/**
	 * Subtract the two coordinates 
	 * @param other
	 * @return coordinate
	 */
	public Coordinate substract(Coordinate other) {
		return new Coordinate(substract(other.getCoordinate()));
	}

	public Coordinate add(Coordinate other) {
		return new Coordinate(add(other.getCoordinate()));
	}
	/**
	 * 
	 * adds two coordinates
	 */
	@Override
	public boolean equals(Object obj) { // equals
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		
		if (!(obj instanceof Coordinate))
			return false;
		Coordinate other = (Coordinate) obj;
		return substract(other.coordinate) == 0.0;
	}
	/**
	 * string
	 */
	@Override
	public String toString() {
		return "Coordinate " + coordinate;
	}
}
