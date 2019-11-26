package elements;



import primitives.*;

import java.util.ArrayList;
import java.util.List;

import geometries.*;

public class Camera {
	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;

	/**
	 * 
	 * *constructor
	 */
	public Camera(Point3D p, Vector vU, Vector vT) {
		p0 = new Point3D(p);
		if (!Coordinate.isZero(vU.dotProduct(vT)))
			throw new IllegalArgumentException("Non-orthogonal vectors for Camera!");
		vUp = new Vector(vU).normalization();
		vTo = new Vector(vT).normalization();
		vRight = vU.crossProduct(vT).normalization();
	}

	/**
	 * 
	 * Getters 
	 */

	public Point3D getP0() {
		return p0;
	}

	public Vector getVup() {
		return vUp;
	}

	public Vector getVto() {
		return vTo;
	}

	public Vector getVright() {
		return vRight;
	}

	// *constructing ray through pixel*//
	public Ray constructRayThroughPixel(int nX, int nY, int i, int j,
			double screenDistance, double screenWidth, double screenHeight) {
		double rY = screenHeight / nY;
		double rX = screenWidth / nX;
		Vector v = vTo.scalar(screenDistance);
		Point3D pij = p0.add(v);
		double xI = (i - (nX / 2.0)) * rX - (rX / 2.0);
		if (xI != 0.0)
			pij = pij.add(vRight.scalar(xI));
		double yJ = (j - (nY / 2.0)) * rY - (rY / 2.0);
		if (yJ != 0.0)
			pij = pij.add(vUp.scalar(-yJ));
		return new Ray(p0, p0.subtract(pij));
	}
	/**
	 * 
	 * this function builds the ray thought the pixel 
	 * @param Nx
	 * @param Ny
	 * @param i
	 * @param j
	 * @param screenDistance
	 * @param screenWidth
	 * @param screenHeight
	 * @return List of Rays through a pixel  
	 */
	public List<Ray> constructRaysThroughPixel(int nX,int nY,int i,int j,	double screenDistance, double screenWidth, double screenHeight){
		List<Ray> listOfRays=new ArrayList<Ray>();
		double rY = screenHeight / nY;
		double rX = screenWidth / nX;
		Vector v = vTo.scalar(screenDistance);
		Point3D pij = p0.add(v);
		double xI = (i - (nX / 2.0)) * rX - (rX / 2.0);
		if (xI != 0.0)
			pij = pij.add(vRight.scalar(xI));
		double yJ = (j - (nY / 2.0)) * rY - (rY / 2.0);
		if (yJ != 0.0)
			pij = pij.add(vUp.scalar(-yJ));
		listOfRays.add(new Ray(p0, p0.subtract(pij)));
		double x=rX/2.0;
		double y=rY/2.0;
		Vector vY1=this.vUp.scalar(rY);
		Vector vX1=this.vRight.scalar(rX);
		Vector vY2=this.vUp.scalar(-rY);
		Vector vX2=this.vRight.scalar(-rX);
		listOfRays.add(new Ray(p0,(p0.subtract(pij.add(vX2.add(vY1))).normalization())));
		listOfRays.add(new Ray(p0,p0.subtract(pij.add(vX2.add(vY2))).normalization()));
		listOfRays.add(new Ray(p0,p0.subtract(pij.add(vX1.add(vY1))).normalization()));
		listOfRays.add(new Ray(p0,p0.subtract(pij.add(vX1.add(vY2))).normalization()));
		return listOfRays;
	}
}