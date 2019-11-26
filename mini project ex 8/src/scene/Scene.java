package scene;

import java.util.ArrayList;
import java.util.List;

import primitives.*;
import elements.AmbientLight;
import elements.Camera;
import elements.Light;
import elements.LightSource;
import geometries.*;

public class Scene {
	private String nameScene;
	private Geometries listofGeom;
	private Camera camera;
	private double distence;
	private Color background;
	private AmbientLight ambientLight;
	private List<LightSource> _Lights;

	/**
	 * constructors
	 */

	public Scene(String nameScene) {
		this.nameScene = nameScene;
		this.listofGeom = new Geometries();
		this.camera = null;
		this.distence = 0;
		this.background = new Color();
		this.ambientLight = new AmbientLight(new Color(java.awt.Color.WHITE), 0.1);
		this._Lights = new ArrayList<LightSource>();
	}

	/**
	 * Getters and Setters
	 */

	public Geometries getListofGeom() {
		return this.listofGeom;
	}

	public void setListofGeom(Geometries g) {
		this.listofGeom = g;
	}

	public Camera getCem() {
		return camera;
	}

	public void setCem(Camera cem) {
		this.camera = cem;
	}

	public double getDistence() {
		return distence;
	}

	public void setDistence(double distence) {
		this.distence = distence;
	}

	public Color getBackground() {
		return this.background;
	}

	public void setBackground(Color back) {
		this.background = back;
	}

	public AmbientLight getAmbientlight() {
		return this.ambientLight;
	}

	public void setAmbientlight(AmbientLight amb) {
		this.ambientLight = amb;
	}

	public List<LightSource> getLights() {
		return _Lights;
	}

	public void setLights(List<LightSource> _light) {
		this._Lights = _light;
	}

	/**
	 * adds a geometry to the  list
	 * @param geomtry
	 */
	public void addGeometry(Geometry geomtry) {
		this.listofGeom.add(geomtry);
	}
}