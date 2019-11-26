package elements;

import primitives.*;

public abstract class Light {

	protected Color color;

	/**
	 * Light constructor
	 * @param col
	 */
	public Light(Color col) {
		color = new Color(col);
	}

	/**
	 * Getter for the color
	 * @return color
	 */
	
	public Color getColor() {
		return new Color(color);
	}

	public Color getIntensity() {
		return new Color(color);
	}

}
