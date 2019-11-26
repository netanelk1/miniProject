package renderer;

import scene.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import elements.LightSource;
import primitives.*;
import geometries.*;

public class Render {
	private Scene scene;
	private ImageWriter imagewriter;

	/**
	 * constructors
	 */

	public Render(Scene scene, ImageWriter imagewriter) {
		this.scene = scene;
		this.imagewriter = imagewriter;
	}

	/**
	 * Gettres
	 */

	public Scene getScene() {
		return scene;
	}

	public void setScene(Scene scene) {
		this.scene = scene;
	}

	public ImageWriter getImagewriter() {
		return imagewriter;
	}

	public void setImagewriter(ImageWriter imagewriter) {
		this.imagewriter = imagewriter;
	}

	/**
	 *  the function returns the closest point to this point
	 * @param intersectionPoints
	 * @return Point3D
	 */

	public Map<Geometry, Point3D> getClosestPoint(Map<Geometry, List<Point3D>> intersectionPoints) {
		double min = Double.MAX_VALUE;
		Map<Geometry, Point3D> point = new HashMap<Geometry, Point3D>();
		for (Entry<Geometry, List<Point3D>> p : intersectionPoints.entrySet()) {
			Geometry key = p.getKey();
			for (Point3D poit : p.getValue()) {
				double d = poit.distance(scene.getCem().getP0());
				if (d < min) {
					min = d;
					point.clear();
					point.put(key, poit);
				}
			}
		}
		return point;
	}
	/**
	 * calculates the color in this point
	 * @param gp
	 * @param inRay
	 * @param level
	 * @param k
	 * @return Color
	 */
	public Color calcColor(GeoPoint gp, Ray inRay, int level, double k) {
		if (level == 0 || Coordinate.isZero(k))
			return new Color();

		Color color = scene.getAmbientlight().getIntensity().add(gp.geometry.getEmission());

		Vector v = inRay.getDirection();
		Vector n = gp.geometry.getNormal(gp.point);
		int nShininiess = gp.geometry.getMaterail().getnShininess();
		double kD = gp.geometry.getMaterail().getKd();
		double kS = gp.geometry.getMaterail().getKs();

		for (LightSource lights : scene.getLights()) {
			Vector l = lights.getL(gp.point);
			if ((n.dotProduct(l)) * (n.dotProduct(v)) > 0) {
				double o = occluded(l, gp);
				if (!Coordinate.isZero(o * k)) {
					Color lightIntensity = (lights.getIntensity(gp.point)).scale(o);
					// Color lightIntensity = (new
					// Color(lights.getIntensity(gp.point)));
					color.add(calcDiffusive(kD, l, n, lightIntensity),
							calcSpecular(kS, l, n, v, nShininiess, lightIntensity));
				}
			}
		}

		// Recursive call for a reflected ray
		Color reflectedLight = new Color();
		double kR = gp.geometry.getMaterail().getKr();
		if (!Coordinate.isZero(kR)) {
			Ray reflectedRay = constructReflectedRay(n, gp.point, inRay);
			if ( reflectedRay != null && scene.getListofGeom().findIntersections(reflectedRay) != null) {
				Map<Geometry, Point3D> reflec = new HashMap<Geometry, Point3D>();
				reflec.putAll(getClosestPoint(scene.getListofGeom().findIntersections(reflectedRay)));
				Geometry g = (Geometry) reflec.keySet().toArray()[0];
				Point3D p = (Point3D) reflec.values().toArray()[0];
				GeoPoint reflectedPoint = new GeoPoint(g, p);
				reflectedLight = calcColor(reflectedPoint, reflectedRay, level - 1, k * kR).scale(kR);
			}
		}
		// Recursive call for a refracted ray
		Color refractedLight = new Color();
		double kT = gp.geometry.getMaterail().getKt();
		if (!Coordinate.isZero(kT)) {
			Ray refractedRay = constructRefractedRay(gp.point, inRay);
			if ( refractedRay != null && scene.getListofGeom().findIntersections(refractedRay) != null) {
				Map<Geometry, Point3D> refrec = new HashMap<Geometry, Point3D>();
				refrec.putAll(getClosestPoint(scene.getListofGeom().findIntersections(refractedRay)));
				Geometry g1 = (Geometry) refrec.keySet().toArray()[0];
				Point3D p1 = (Point3D) refrec.values().toArray()[0];
				GeoPoint refractedPoint = new GeoPoint(g1, p1);
				refractedLight = calcColor(refractedPoint, refractedRay, level - 1, k * kT).scale(kT);
			}
		}
		color.add(reflectedLight, refractedLight);
		return color;

	}

	private Color calcColor(GeoPoint gp, Ray r) {
		return calcColor(gp, r, 3, 1.0);
	}

	public void printGrid(int interval) { // assuming that interval is between
											// the
											// lines of the grid
		for (int i = 0; i < imagewriter.getWidth(); i++) {
			for (int j = 0; j < imagewriter.getHeight(); j++) {
				if (i % interval == 0 || j % interval == 0) {
					imagewriter.writePixel(i, j, 255, 255, 255);
				}
			}
		}
	}
/*
	public void renderImage() {
		for (int i = 0; i < imagewriter.getNx(); i++) {
			for (int j = 0; j < imagewriter.getNy(); j++) {

				Ray ray = scene.getCem().constructRayThroughPixel(imagewriter.getNx(), imagewriter.getNy(), i, j,
						scene.getDistence(), imagewriter.getWidth(), imagewriter.getHeight());
				Map<Geometry, List<Point3D>> intersectionsPoints = new HashMap<Geometry, List<Point3D>>();
				if (scene.getListofGeom().findIntersections(ray) == null) {
					imagewriter.writePixel(i, j, scene.getBackground().getColor());
				} else {
					intersectionsPoints.putAll(scene.getListofGeom().findIntersections(ray));
					Geometry geo = null;
					Point3D p = null;
					Map<Geometry, Point3D> ClosestPoint = getClosestPoint(intersectionsPoints);
					for (Entry<Geometry, Point3D> s : ClosestPoint.entrySet()) {
						geo = s.getKey();
						p = s.getValue();
					}
					GeoPoint gp = new GeoPoint(geo, p);
					// System.out.println(p.toString());
					imagewriter.writePixel(i, j, calcColor(gp, ray).getColor());
				}
			}
		}
	}
	*/
	/**
	 * this function render this image
	 */
	public void renderImage(){
		for (int i = 0; i < imagewriter.getNx(); i++) {
			for (int j = 0; j < imagewriter.getNy(); j++) {
				Color color=new Color();
				List<Ray> rayList=new ArrayList<Ray>();
				rayList.addAll( scene.getCem().constructRaysThroughPixel(imagewriter.getNx(), imagewriter.getNy(), i, j,
						scene.getDistence(), imagewriter.getWidth(), imagewriter.getHeight()));
				for(int z=0;z<5;z++)
				{
					Ray ray=rayList.get(z);
					Map<Geometry, List<Point3D>> intersectionsPoints = new HashMap<Geometry, List<Point3D>>();
					if (scene.getListofGeom().findIntersections(ray) == null) {
						color.add(scene.getBackground());
					} else {
						intersectionsPoints.putAll(scene.getListofGeom().findIntersections(ray));
						Geometry geo = null;
						Point3D p = null;
						Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionsPoints);
						for (Entry<Geometry, Point3D> s : closestPoint.entrySet()) {
							geo = s.getKey();
							p = s.getValue();
						}
						GeoPoint gp = new GeoPoint(geo, p);
						// System.out.println(p.toString());
						color.add(calcColor(gp, ray));
					}
				}
				imagewriter.writePixel(i, j,(color.reduce(5)).getColor());
			}
		}
		
		
	}
	




	/**
	 * 
	 * @param kD
	 * @param l
	 * @param n
	 * @param lightIntensity
	 * @return the calculation of the diffuse light of some lightsource
	 */


	public Color calcDiffusive(double kD, Vector l, Vector n, Color lightIntensity) {
		return new Color(lightIntensity).scale(kD * Math.abs(l.dotProduct(n)));
	}

	/**
	 * 
	 * @param kS
	 * @param l
	 * @param n
	 * @param v
	 * @param nShininiess
	 * @param lightIntensity
	 * @return the calculation of the specular light of some lightsource
	 */
	public Color calcSpecular(double kS, Vector l, Vector n, Vector v, int nShininiess, Color lightIntensity) {
		double w = -2 * (l.dotProduct(n));
		if (Coordinate.isZero(w))
			return null;
		Vector r = l.add(n.scalar(w));
		double vr = r.dotProduct(v);
		if (vr > 0 || Coordinate.isZero(vr))
			return new Color();
		return (lightIntensity.scale(kS * Math.pow(-vr, nShininiess)));

	}

	/**
	 * 
	 * @param n
	 * @param p
	 * @param inRay
	 * @return the reflected ray that come back from this point
	 */
	private Ray constructReflectedRay(Vector n, Point3D p, Ray inRay) {
		Vector v = inRay.getDirection();
		double vn = v.dotProduct(n);
		if (Coordinate.isZero(vn))
			return null;
		Vector w = n.scalar(-2 * vn);
		Vector r = v.add(w);
		return new Ray(p, r);
	}

	/**
	 * 
	 * @param p
	 * @param inRay
	 * @return the refracted ray 
	 */
	private Ray constructRefractedRay(Point3D p, Ray inRay) {
		Vector v = inRay.getDirection();
		return new Ray(p, v);
	}

	
	/**
	 * 
	 * @param L
	 * @param gp
	 * @return the calculation of shadow in some point
	 */
	
	public double occluded(Vector L, GeoPoint gp) {
		Vector lightDirection = L.scalar(-1); // from point to light source
		Vector normal = gp.geometry.getNormal(gp.point);
		Vector epsVector = normal.scalar(normal.dotProduct(lightDirection) > 0 ? 2 : -2);
		Point3D geometryPoint = gp.point.add(epsVector);
		Ray lightRay = new Ray(geometryPoint, lightDirection);

		double shadowK = 1;
		Map<Geometry, List<Point3D>> intersectionPoints = scene.getListofGeom().findIntersections(lightRay);
		if (intersectionPoints == null) {
			return shadowK;
		} else {
			for (Entry<Geometry, List<Point3D>> entry : intersectionPoints.entrySet()) {
				shadowK *= entry.getKey().getMaterail().getKt() * entry.getValue().size();
			}
			return shadowK;
		}
	}

	/*
	 * public boolean occluded(Vector L, GeoPoint gp) { Vector lightDirection =
	 * L.scalar(-1); // from point to light source Vector normal =
	 * gp.geometry.get_normal(gp.point); Vector epsVector =
	 * normal.scalar(normal.dotProduct(lightDirection) > 0 ? 2 : -2); Point3D
	 * geometryPoint =gp. point.add(epsVector); Ray lightRay = new
	 * Ray(geometryPoint, lightDirection);
	 * 
	 * 
	 * if(scene.getListofGeom().findIntersections(lightRay)==null) { return
	 * false; } else{ return true; } }
	 */
}