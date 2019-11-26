package elements;

import primitives.*;

public class AmbientLight extends Light {
	private double kc;
	private Color intensity;

	/***
	 * 
	 * Getters
	 * @returns kC
	 */
	public double getKa() {
		return kc;
	}

	/**
	 * constructor of AmbientLight
	 * @param color
	 * @param k
	 */
	public AmbientLight(Color color, double k) {
		super(color);
		this.kc = k;
		intensity = new Color(color).scale(k);
	}

	/**
	 * 
	 * get intensity
	 * @return the color intesity of the light
	 */
	@Override
	public Color getIntensity() {
		return new Color(intensity);
	}

}
