package primitives;

/**
 * Wrapper class for Color instead of java.awt.Color by three colors: red,
 * green, blue
 * 
 
 *
 */
public class Color {

	private double _red;
	private double _green;
	private double _blue;

	public static Color WHITE = new Color(255, 255, 255);
	public static Color BLACK = new Color(0, 0, 0);
	
	/********** Constructors ***********/
	public Color() {
		_red = 0.0;
		_green = 0.0;
		_blue = 0.0;
	}

	public Color(double red, double green, double blue) {
		// if (red>255 || green>255 || blue>255 || red<0 || green<0 || blue<0 )
		// throw new ArithmeticException("colors in java has limits, color out
		// of bound");
		_red = red < 0 ? 0 : red;
		_green = green < 0 ? 0 : green;
		_blue = blue < 0 ? 0 : blue;
	}

	public Color(Color other) {
		_red = other._red;
		_green = other._green;
		_blue = other._blue;
	}

	public Color(java.awt.Color color) {
		_red = color.getRed();
		_green = color.getGreen();
		_blue = color.getBlue();
	}

	/************** Getters/Setters *******/
	public Color setColor() {
		_red = 0.0;
		_green = 0.0;
		_blue = 0.0;
		return this;
	}

	public Color setColor(double red, double green, double blue) {
		_red = red;
		_green = green;
		_blue = blue;
		return this;
	}

	public Color setColor(Color other) {
		_red = other._red;
		_green = other._green;
		_blue = other._blue;
		return this;
	}

	public Color setColor(java.awt.Color color) {
		_red = color.getRed();
		_green = color.getGreen();
		_blue = color.getBlue();
		return this;
	}

	public double get_red() {
		return this._red;
	}

	public double get_green() {
		return this._green;
	}

	public double get_blue() {
		return this._blue;
	}

	public void set_red(double r) {
		this._red = r;
	}

	public void set_green(double g) {
		this._green = g;
	}

	public void set_blue(double b) {
		this._blue = b;
	}

	/**
	 * Getter for the color - produces final java.awt.Color
	 * 
	 * @return color in java.awt type
	 */
	public java.awt.Color getColor() {
		int r = (int) _red;
		int g = (int) _green;
		int b = (int) _blue;
		return new java.awt.Color(r > 255 ? 255 : r, g > 255 ? 255 : g, b > 255 ? 255 : b);
	}

	/********** Operations ************/

	// add two colors, makes sure rules
	/**
	 * @param colors
	 *            to add to our color
	 * @return our color with adding colors
	 */
	public Color add(Color... colors) {
		for (Color color : colors) {
			_red += color._red;
			_green += color._green;
			_blue += color._blue;
		}
		return this;
	}

	/**
	 * 
	 * @param exp
	 *            the value we want to scale our color
	 * @return a color that his intensity scaling by exp
	 */
	public Color scale(double exp) {
		if (exp < 0 || exp > 1)
			throw new ArithmeticException("Scaling color with a value out of 0 to 1 range");
		_red *= exp;
		_green *= exp;
		_blue *= exp;
		return this;
	}

	/**
	 * 
	 * @param exp
	 *            the value we want to reduce our color
	 * @return a color that his intensity reducing by exp
	 */
	public Color reduce(double exp) {
		if (exp < 1)
			throw new ArithmeticException("Reducing color with a value smaller than 1");
		_red /= exp;
		_green /= exp;
		_blue /= exp;
		return this;
	}

}
