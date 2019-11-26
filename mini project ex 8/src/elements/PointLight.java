package elements;

import primitives.*;

public class PointLight extends Light implements LightSource {

	protected Point3D _Position;
	protected double _Kc;
	protected double kL;
	protected double kQ;

	/**
	 * PointLight constructor
	 *  @param col
	 *   @param _Position
	 *    @param _Kc
	 *     @param _Kl
	 *      @param _Kq
	 */
	public PointLight(Color col, Point3D position, double kC, double kL, double kQ) {
		super(col);
		_Position = new Point3D(position);
		this._Kc = kC;
		this.kL = kL;
		this.kQ = kQ;
	}

	/**
	 * Getter for the position point
	 * @return position
	 */

	public Point3D getPosition() {
		return _Position;
	}

	
	/**
	 * find the Vector L by substruct the point minus the position
	 * @return normalized vector of substract the point minus the position
	 */
	
	@Override
	public Vector getL(Point3D p) {
		return (_Position.subtract(p)).normalization();
	}
	/**
	 * @return  the vector L in vector D
	 */
	@Override
	public Vector getD(Point3D p) {
		return (getL(p));
	}

	/**
	 * find the intensity in specific point
	 * @return intensity
	 */
	@Override
	public Color getIntensity(Point3D p) {
		double distance = p.distance(_Position);
		double num = _Kc + kL * distance + kQ * distance * distance;
		Color intensity = getIntensity().reduce(num);
		return intensity;
	}

}
