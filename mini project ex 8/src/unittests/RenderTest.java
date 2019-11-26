package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import geometries.*;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;
import elements.*;

public class RenderTest {
	@Test
	public void basicRendering() {
		Scene scene = new Scene("test scene");
		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setBackground(Color.BLACK);
		scene.setAmbientlight(new AmbientLight(Color.WHITE, 0.2));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(50, new Point3D(0, 0, 150), Color.BLACK, new Material(0, 0, 0,0,0)));

		geometries.add(new Triangle(new Point3D(100, 0, 149), new Point3D(0, 100, 149), new Point3D(100, 100, 149),
				new Color(java.awt.Color.GREEN), new Material(1, 0.8, 5,0,0)));

		geometries.add(new Triangle(new Point3D(100, 0, 149), new Point3D(0, -100, 149), new Point3D(100, -100, 149),
				new Color(java.awt.Color.BLUE), new Material(1, 0.8, 5,0,0)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, 100, 149), new Point3D(-100, 100, 149),
				new Color(java.awt.Color.RED), new Material(1, 0.8, 5,0,0)));

		geometries.add(new Triangle(new Point3D(-100, 0, 149), new Point3D(0, -100, 149), new Point3D(-100, -100, 149),
				Color.BLACK, new Material(1, 0.8, 5,0,0)));
		scene.setListofGeom(geometries);
		scene.setDistence(150);

		ImageWriter imageWriter = new ImageWriter("emission rendering", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.printGrid(50);
		render.getImagewriter().writeToimage();
	}

	@Test
	public void SpotLightTest() {
		Scene scene = new Scene("Test Scene3");
		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistence(350);
		scene.setBackground(new Color());
		scene.setAmbientlight(new AmbientLight(Color.WHITE, 0));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(50, new Point3D(0, 0, 150), new Color(20,20,20), new Material(0.8, 0.7, 100,0,0)));
		List<LightSource> lights=new ArrayList<LightSource>();
		lights.add(new SpotLight(new Color(200, 200, 200),new Point3D(0,60, 100), 1, 0, 0,
				new Vector(0,-1,1)));
		scene.setLights(lights);
		scene.setListofGeom(geometries);
		ImageWriter imageWriter = new ImageWriter("SpotLight", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.printGrid(50);
		render.getImagewriter().writeToimage();
	}

	@Test
	public void DirectionalLightTest() {
		Scene scene = new Scene("Test Scene3");
		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistence(350);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientlight(new AmbientLight(new Color(255,255, 255),0.2));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(50, new Point3D(0, 0, 150), new Color(125, 92, 168), new Material(0.66, 0.9,20,0,0)));
		List<LightSource> lights=new ArrayList<LightSource> ();
		lights.add(new DirectionalLight(new Color(128, 128, 128), new Vector(-1, 1,1)));
		scene.setLights(lights);
		scene.setListofGeom(geometries);
		ImageWriter imageWriter = new ImageWriter("first test", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		render.printGrid(50);
		render.getImagewriter().writeToimage();
	}

	@Test
	public void PointLightTest() {
		Scene scene = new Scene("Test Scene3");

		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistence(350);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientlight(new AmbientLight(new Color(255, 255, 255), 0.2));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(50, new Point3D(0, 0, 150), new Color(0,0,128), new Material(0.8, 0.7,200,0,0)));
		List<LightSource> lights=new ArrayList<LightSource> ();
		lights.add(new PointLight(new Color(200,200,200),new Point3D(20, 30,90), 1, 0, 0));
		scene.setLights(lights);
		scene.setListofGeom(geometries);
		ImageWriter imageWriter = new ImageWriter("PointLight Test", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.printGrid(50);
		render.getImagewriter().writeToimage();

	}
	@Test
	public void PointLightTest2(){
		Scene scene = new Scene("Test Scene3");

		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistence(350);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientlight(new AmbientLight(new Color(255, 255, 255), 0.2));
		Geometries geometries = new Geometries();
		Triangle triangle1 = new Triangle(new Point3D(-250,-250,500),new Point3D(-250,250,500),new Point3D(250,-250,500),new Color(255,0,0),new Material(0.9, 0.8, 100,0,0));
	    Triangle triangle2 = new Triangle(new Point3D(250,250,500),new Point3D(-250,250,500),new Point3D(250,-250,500),new Color(255,0,0),new Material(0.9, 0.8, 100,0,0));
		geometries.add(triangle1);
		geometries.add(triangle2);
		List<LightSource> lights=new ArrayList<LightSource> ();
		lights.add(new PointLight(new Color(150,150,150),new Point3D(20, 30,90), 1, 0, 0));
		scene.setLights(lights);
		scene.setListofGeom(geometries);
		ImageWriter imageWriter = new ImageWriter("PointLight Test2", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.printGrid(50);
		render.getImagewriter().writeToimage();	
	}
	@Test
	public void SpotLightTest3(){
		Scene scene=new Scene("SpotLight 3");
		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistence(350);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientlight(new AmbientLight(new Color(255, 255, 255), 0.2));
		Geometries geometries = new Geometries();
		Triangle triangle1 = new Triangle(new Point3D(-250,-250,500),new Point3D(-250,250,500),new Point3D(250,-250,500),new Color(0,0,0),new Material(0.9, 0.8, 100,0,0));
	    Triangle triangle2 = new Triangle(new Point3D(250,250,500),new Point3D(-250,250,500),new Point3D(250,-250,500),new Color(0,0,0),new Material(0.9, 0.8, 100,0,0));
		geometries.add(triangle1);
		geometries.add(triangle2);
		scene.setListofGeom(geometries);
		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(new SpotLight(new Color(255,255,255),new Point3D(0,0,10), 1, 0, 0,new Vector(0,0,1)));
		scene.setLights(lights);
		ImageWriter imageWriter = new ImageWriter("SpotLight 3", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);
		render.renderImage();
		render.printGrid(50);
		render.getImagewriter().writeToimage();
	}
	@Test
	public void SpotLightTest2(){
		
		Scene scene = new Scene("SpotLight 2");
		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		scene.setDistence(350);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientlight(new AmbientLight(new Color(255, 255, 255), 0.2));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(50, new Point3D(0, 0, 150), new Color(0,0,128), new Material(0.8, 0.7,200,0,0)));
		List<LightSource> lights=new ArrayList<LightSource> ();
		lights.add(new SpotLight(new Color(200,200,200),new Point3D(20, 30,90), 1, 0, 0,new Vector(0,-1,1)));
		scene.setLights(lights);
		scene.setListofGeom(geometries);
		ImageWriter imageWriter = new ImageWriter("SpotLight 2", 500, 500, 500, 500);
		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.printGrid(50);
		render.getImagewriter().writeToimage();
	}
	
	@Test
	  public void shadowTest() {
	    Scene scene = new Scene("Test shadow");
	    scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
	    scene.setDistence(200);
	    scene.setBackground(new Color(0,0,0));
	    Geometries geometries = new Geometries();
	    Sphere sphere = new Sphere( 50,new Point3D(0, 0, 150), new Color(241, 6, 151),new Material(0.9,0.8,300,0,0));
	    Triangle triangle1 = new Triangle(new Point3D(-250,-250,250),new Point3D(-250,250,250),new Point3D(250,-250,250),new Color(0,0,0),new Material(0.9, 0.8, 100,0,0));
	    Triangle triangle2 = new Triangle(new Point3D(250,250,250),new Point3D(-250,250,250),new Point3D(250,-250,250),new Color(0,0,0),new Material(0.9, 0.8, 100,0,0));
	    geometries.add(sphere);
	    geometries.add(triangle1);
	    geometries.add(triangle2);
	    scene.setListofGeom(geometries);
	    List<LightSource> lights = new ArrayList<LightSource>();
	    lights.add(new SpotLight( new Color(200,200,200) ,new Point3D(20,10,30), 1,0,0, new Vector(0,0,1)));
	    scene.setLights(lights);
	    ImageWriter imageWriter = new ImageWriter("shadow test", 500, 500, 500, 500);
	    Render render = new Render(scene, imageWriter);
	    render.renderImage();
	    render.printGrid(50);
	    render.getImagewriter().writeToimage();
	    
	  }
	@Test
	public void recursiveTest(){
		Scene scene = new Scene("recursiveTest 1");
		scene.setDistence(300);
		scene.setBackground(new Color(0,0,0));
		scene.setAmbientlight(new AmbientLight(new Color(255,255,255),0.2));
		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));
		Sphere sphere = new Sphere(500, new Point3D(0.0, 0.0, -1000), new Color(0, 0, 100),
				new Material(0.5, 0.3, 10, 0, 0.5));
		scene.addGeometry(sphere);
		
		Sphere sphere2 = new Sphere(250, new Point3D(0.0, 0.0, -1000), new Color(100, 20, 20),
				new Material(0.4, 0.4, 10, 0.5, 0));


		scene.addGeometry(sphere2);
		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(new SpotLight(new Color(255, 100, 100),new Point3D(-200, -200, -150), 0.1, 0.01, 0.0025,
				new Vector(2, 2, -3)));
		
		scene.setLights(lights);
		ImageWriter imageWriter = new ImageWriter("Recursive Test", 500, 500, 500, 500);
		
		Render render = new Render(scene,imageWriter);
		render.renderImage();
		render.getImagewriter().writeToimage();
	
	}
	@Test
	public void recursiveTest2() {

		Scene scene = new Scene("recursive2");
		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));
		scene.setDistence(300);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientlight(new AmbientLight(new Color(255,255,255),0.2));

		Sphere sphere = new Sphere(300, new Point3D(-550, -500, -1000), new Color(0, 0, 100),
				new Material(0.4, 0.6, 10, 0, 0.5));
		scene.addGeometry(sphere);

		Sphere sphere2 = new Sphere(150, new Point3D(-550, -500, -1000), new Color(100, 20, 20),
				new Material(0.3, 0.4, 10, 0.5, 0));
		scene.addGeometry(sphere2);

		Triangle triangle = new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
				new Point3D(200, 200, -375), new Color(20, 20, 20), new Material(0.4, 0.6, 10, 1, 0));

		Triangle triangle2 = new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
				new Point3D(-1500, -1500, -1500), new Color(20, 20, 20), new Material(0.4, 0.6, 10, 0.5, 0));

		scene.addGeometry(triangle);
		scene.addGeometry(triangle2);

		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(new SpotLight(new Color(255, 100, 100),new Point3D(200, 200, -150), 0, 0.0001, 0.005,
				new Vector(-2, -2, -3)));
		scene.setLights(lights);

		ImageWriter imageWriter = new ImageWriter("Recursive Test 2", 500, 500, 500, 500);

		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.getImagewriter().writeToimage();
	}
	@Test
	public void recursiveTest3() {

		Scene scene = new Scene("recursive3");
		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0, -1)));
		scene.setDistence(300);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientlight(new AmbientLight(new Color(255,255,255),0.2));
		
		Sphere sphere = new Sphere(300, new Point3D(0, 0, -1500), new Color(0, 0, 100), new Material(0.5, 0.3, 10, 0, 0.5));
		scene.addGeometry(sphere);

		Sphere sphere2 = new Sphere(150, new Point3D(0, 0, -1500), new Color(100, 20, 20), new Material(0.4, 0.6, 10, 0.5, 0));
		scene.addGeometry(sphere2);

		Triangle triangle = new Triangle(new Point3D(2000, -1000, -2000), new Point3D(-1000, 2000, -2000),
				new Point3D(700, 700, -875), new Color(20, 20, 20), new Material(0.5, 0.3, 10, 1, 0));

		Triangle triangle2 = new Triangle(new Point3D(2000, -1000, -2000), new Point3D(-1000, 2000, -2000),
				new Point3D(-1000, -1000, -2000), new Color(20, 20, 20), new Material(0.55, 0.25, 10, 0.5, 0));

		scene.addGeometry(triangle);
		
		scene.addGeometry(triangle2);


		List<LightSource> lights = new ArrayList<LightSource>();
		lights.add(new SpotLight(new Color(255, 100, 100),new Point3D(200, 200, -650), 0, 0.0001, 0.005,
				new Vector(-2, -2, -3)));
		scene.setLights(lights);

		ImageWriter imageWriter = new ImageWriter("Recursive Test 3", 500, 500, 500, 500);

		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.getImagewriter().writeToimage();

	}
	@Test
	public void checking()
	{
		Scene scene = new Scene("checking");

		scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, 1, 0), new Vector(0, 0,- 1)));
		scene.setDistence(350);
		scene.setBackground(new Color(0, 0, 0));
		scene.setAmbientlight(new AmbientLight(new Color(255, 255, 255), 0.2));
		Geometries geometries = new Geometries();
		geometries.add(new Sphere(50, new Point3D(50,50,-150), new Color(255, 255, 255), new Material(0.66, 0.9,20,0,0)));
		scene.setListofGeom(geometries);
		ImageWriter imageWriter = new ImageWriter("checking", 500, 500, 500, 500);

		Render render = new Render(scene, imageWriter);

		render.renderImage();
		render.getImagewriter().writeToimage();
	}
	@Test
	public void test() {
		Scene scene=new Scene("pool Table");
		scene.setCem(new Camera(new Point3D(50, 50, 50), new Vector(0, 1, 0), new Vector(0, 0, -1)));
		scene.setDistence(100);
		scene.setBackground(new Color(153, 153, 102));
		List<LightSource> lights = new ArrayList<LightSource>();
		//the surface of the table
		Triangle triangle1 =new Triangle(new Point3D(-100,50,-60),new Point3D(100,-50,-60),new Point3D(200,50,-60),new Color(0,255,0),new Material(0,0,0,0,0));
		Triangle triangle2 =new Triangle(new Point3D(-100,50,-60),new Point3D(100,-50,-60),new Point3D(-200,-50,-60),new Color(0,255,0),new Material(0,0,0,0,0));
		//the sides
		Triangle triangle3 =new Triangle(new Point3D(-200,-70,-60),new Point3D(-200,-50,-60),new Point3D(100,-50,-60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
		Triangle triangle4=new Triangle(new Point3D(-200, -70, -60), new Point3D(100, -70, -60), new Point3D(100, -50, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
		Triangle triangle5 = new Triangle(new Point3D(100, -50, -60),new Point3D(100, -70, -60), new Point3D(200, 50, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
	    Triangle triangle6 = new Triangle(new Point3D(200, 30, -60),new Point3D(100, -70, -60), new Point3D(200, 50, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
	    //the legs of the table
	    Triangle triangle7 = new Triangle(new Point3D(80, -70, -60),new Point3D(80, -200, -60),new Point3D(100, -200, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle8 = new Triangle(new Point3D(80, -70, -60),new Point3D(100, -70, -60),new Point3D(100, -200, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle9 = new Triangle(new Point3D(100, -70, -60),new Point3D(110, -60, -60),new Point3D(100, -200, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle10 = new Triangle(new Point3D(110, -180, -60),new Point3D(110, -60, -60),new Point3D(100, -200, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle11 = new Triangle(new Point3D(170, -120, -60),new Point3D(190, -120, -60),new Point3D(170, 0, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle12 = new Triangle(new Point3D(190, 20, -60),new Point3D(190, -120, -60),new Point3D(170, 0, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle13 = new Triangle(new Point3D(190, 20, -60), new Point3D(200, 30, -60),new Point3D(190, -120, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle14 = new Triangle(new Point3D(200, -100, -60),new Point3D(200, 30, -60),new Point3D(190, -120, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle15 = new Triangle(new Point3D(-200, -70, -60),new Point3D(-180, -200, -60),new Point3D(-200, -200, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle16 = new Triangle(new Point3D(-200, -70, -60),new Point3D(-180, -200, -60),new Point3D(-180, -70, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle17 = new Triangle(new Point3D(-170, -70, -60),new Point3D(-180, -70, -60),new Point3D(-180, -200, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle18 = new Triangle(new Point3D(-170, -70, -60),new Point3D(-170, -180, -60),new Point3D(-180, -200, -60),new Color(128,0,0),new Material(0.7,0.8,50,0,0));
        Triangle triangle19= new Triangle(new Point3D(150,0,-55),new Point3D(35,0,-55),new Point3D(35,-5,-55),new Color(128,0,0),new Material(0.4,0.5,5,0,0));
        Triangle triangle20=new Triangle(new Point3D(150,0,-55),new Point3D(150,-5,-55),new Point3D(35,-5,-55),new Color(128,0,0),new Material(0.4,0.5,5,0,0));
        Triangle triangle21 = new Triangle(new Point3D(500,-320,-100),new Point3D(-500,150,-100),new Point3D(-500,-320,-100),new Color(0,100,40),new Material(1, 1, 5,0,0));
	    Triangle triangle22 = new Triangle(new Point3D(500,-320,-100),new Point3D(-500,150,-100),new Point3D(500,150,-100),new Color(0,100,40),new Material(1, 1, 5,0,0));
	    Sphere Sphere =new Sphere(3,new Point3D(0,0,0), Color.WHITE,new Material(1,1,30,0,0));
	    Sphere Sphere1 =new Sphere(5,new Point3D(-40,0,-55), Color.BLACK,new Material(1,1,30,0,0));
        Sphere Sphere2 =new Sphere(5, new Point3D(30,0,-55),Color.WHITE,new Material(0.4,0,20,1,0));
        Sphere Sphere3 =new Sphere(5, new Point3D(-50,5,-55),new Color(215, 116, 0),new Material(0.4,0,20,1,0));
        Sphere Sphere4 =new Sphere(5, new Point3D(-50,-5,-55),new Color(0,0,147),new Material(0.4,0,20,1,0));
        Sphere Sphere5 =new Sphere(5, new Point3D(-60,0,-55),new Color(200,0,0),new Material(0.4,0,20,1,0));
        Sphere Sphere6 =new Sphere(5, new Point3D(-60,10,-55),new Color(0,0,80),new Material(0.4,0,20,1,0));
        Sphere Sphere7 =new Sphere(5, new Point3D(-60,-10,-55),new Color(230, 138, 0),new Material(0.4,0,20,1,0));
        Sphere Sphere8 =new Sphere(5, new Point3D(-70,5,-55),new Color(0, 153, 0),new Material(0.4,0,20,1,0));
        Sphere Sphere9 =new Sphere(5, new Point3D(-70,-5,-55),new Color(204,0,0),new Material(0.4,0,20,1,0));
        Sphere Sphere10 =new Sphere(5, new Point3D(-70,15,-55),new Color(204, 0, 204),new Material(0.4,0,20,1,0));
        Sphere Sphere11 =new Sphere(5, new Point3D(-70,-15,-55),new Color(10, 190, 0),new Material(0.4,0,20,1,0));
        Sphere Sphere12 =new Sphere(5, new Point3D(-80,0,-55),new Color(153, 0, 204),new Material(0.4,0,20,1,0));
        Sphere Sphere13=new Sphere(5, new Point3D(-80,10,-55),new Color(102, 0, 51),new Material(0.4,0,20,1,0));
        Sphere Sphere14=new Sphere(5, new Point3D(-80,-10,-55),new Color(0, 153, 51),new Material(0.4,0,20,1,0));
        Sphere Sphere15=new Sphere(5, new Point3D(-80,-20,-55),new Color(255, 0, 255),new Material(0.4,0,20,1,0));
        Sphere Sphere16=new Sphere(5, new Point3D(-80,20,-55),new Color(102, 0, 102),new Material(0.4,0,20,1,0));
        Sphere Sphere17 =new Sphere(3,new Point3D(70,0,0), Color.WHITE,new Material(1,1,30,0,0));
        Sphere Sphere18 =new Sphere(3,new Point3D(-70,0,0), Color.WHITE,new Material(1,1,30,0,0));
        Sphere Sphere19 =new Sphere(3,new Point3D(110,50,0), Color.WHITE,new Material(1,1,30,0,0));
        Sphere Sphere20 =new Sphere(3,new Point3D(-20,50,0), Color.WHITE,new Material(1,1,30,0,0));
        Sphere Sphere21 =new Sphere(3,new Point3D(40,50,0), Color.WHITE,new Material(1,1,30,0,0));
        Triangle lamp1=new Triangle(new Point3D(0,50,-200),new Point3D(5,50,-100),new Point3D(-5,50,-190),new Color(0,153,0),new Material( 0.5,0.3,5,1,1));
        lights.add(new PointLight(new Color(Color.WHITE),new Point3D(0,50,-200),0.7,0.6,0.6));
        lights.add(new PointLight(new Color(Color.WHITE),new Point3D(15,50,-200),0.7,0.6,0.6));
        lights.add(new PointLight(new Color(Color.WHITE),new Point3D(25,50,-200),0.7,0.6,0.6));
        Triangle lamp2=new Triangle( new Point3D(15,50,-200),new Point3D(20,50,-190),new Point3D(10,50,-190),new Color(0,153,0),new Material(0.5,0.3,5,1,1));
     //   Triangle lamp3= new Triangle(new Point3D(25,50,200),new Point3D(35,50,200),new Point3D(20,50,200),new Color(0,153,0),new Material(0.5,0.3,5,1,1));
        scene.addGeometry(triangle1);
        scene.addGeometry(Sphere);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);
        scene.addGeometry(triangle5);
        scene.addGeometry(triangle6);
        scene.addGeometry(triangle7);
        scene.addGeometry(triangle8);
        scene.addGeometry(triangle9);
        scene.addGeometry(triangle10);
        scene.addGeometry(triangle11);
        scene.addGeometry(triangle12);
        scene.addGeometry(triangle13);
        scene.addGeometry(triangle14);
        scene.addGeometry(triangle15);
        scene.addGeometry(triangle16);
        scene.addGeometry(triangle17);
        scene.addGeometry(triangle18);
        scene.addGeometry(triangle19);
        scene.addGeometry(triangle20);
        scene.addGeometry(triangle21);
        scene.addGeometry(triangle22);
        scene.addGeometry(Sphere1);
        scene.addGeometry(Sphere2);
        scene.addGeometry(Sphere3);
        scene.addGeometry(Sphere4);
        scene.addGeometry(Sphere5);
        scene.addGeometry(Sphere6);
        scene.addGeometry(Sphere7);
        scene.addGeometry(Sphere8);
        scene.addGeometry(Sphere9);
        scene.addGeometry(Sphere10);
        scene.addGeometry(Sphere11);
        scene.addGeometry(Sphere12);
        scene.addGeometry(Sphere13);
        scene.addGeometry(Sphere14);
        scene.addGeometry(Sphere15);
        scene.addGeometry(Sphere16);
        scene.addGeometry(Sphere17);
        scene.addGeometry(Sphere18);
        scene.addGeometry(Sphere19);
        scene.addGeometry(Sphere20);
        scene.addGeometry(Sphere21);
        scene.addGeometry(lamp1);
        scene.addGeometry(lamp2);
        //scene.addGeometry(lamp3);
        scene.setLights(lights);
        ImageWriter imageWriter=new ImageWriter("Pool table test",500,500,500,500);
        Render render = new Render(scene, imageWriter);
        render.renderImage();
        render.getImagewriter().writeToimage();
        
	}
        

	@Test
	 public void ShadowTets2(){
		Scene scene = new Scene("Test shadow2");
	    scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
	    scene.setDistence(120);
	    scene.setBackground(new Color(0,0,0));
	    Geometries geometries = new Geometries();
	    Sphere sphere = new Sphere( 100,new Point3D(0, 0, 150), new Color(241, 6, 151),new Material(0.5,0.8,300,0,0));
	    Triangle triangle = new Triangle(new Point3D(5,5,40),new Point3D(5,45,40),new Point3D(45,5,40),new Color(241, 6, 0),new Material(0.5, 0.4, 50,0,0));
	    geometries.add(sphere);
	    geometries.add(triangle);
	    scene.setListofGeom(geometries);
	    List<LightSource> lights = new ArrayList<LightSource>();
	    lights.add(new SpotLight( new Color(200,200,200) ,new Point3D(20,20,-60), 1,0,0, new Vector(0,0,1)));
	    scene.setLights(lights);
	    ImageWriter imageWriter = new ImageWriter("shadow test2", 500, 500, 500, 500);
	    Render render = new Render(scene, imageWriter);
	    render.renderImage();
	    render.printGrid(50);
	    render.getImagewriter().writeToimage();
	}
	@Test
	public void ShadowTets3(){
		  Scene scene = new Scene("Test shadow2");
		    scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		    scene.setDistence(120);
		    scene.setBackground(new Color(0,0,0));
		    Geometries geometries = new Geometries();
		    Sphere sphere = new Sphere( 100,new Point3D(0, 0, 150), new Color(241, 6, 151),new Material(0.9,0.8,300,0,0));
		    Triangle triangle = new Triangle(new Point3D(10,10,40),new Point3D(10,50,40),new Point3D(50,10,40),new Color(241,6,0),new Material(0.5, 0.4, 50,0,0));
		    geometries.add(sphere);
		    geometries.add(triangle);
		    scene.setListofGeom(geometries);
		    List<LightSource> lights = new ArrayList<LightSource>();
		    lights.add(new SpotLight(new Color(200,200,200) ,new Point3D(20,20,-60), 1,0,0, new Vector(0,0,1)));
		    scene.setLights(lights);
		    ImageWriter imageWriter = new ImageWriter("shadow test3", 500, 500, 500, 500);
		    Render render = new Render(scene, imageWriter);
		    render.renderImage();
		    render.printGrid(50);
		    render.getImagewriter().writeToimage();
	}
	@Test
	public void ShadowTets4(){
		  Scene scene = new Scene("Test shadow2");
		    scene.setCem(new Camera(new Point3D(0, 0, 0), new Vector(0, -1, 0), new Vector(0, 0, 1)));
		    scene.setDistence(120);
		    scene.setBackground(new Color(0,0,0));
		    Geometries geometries = new Geometries();
		    Sphere sphere = new Sphere( 100,new Point3D(0, 0, 150), new Color(241, 6, 151),new Material(0.9,0.8,300,0,0));
		    Triangle triangle = new Triangle(new Point3D(15,15,40),new Point3D(15,55,40),new Point3D(55,15,40),new Color(241,6,0),new Material(0.5, 0.4, 50,0,0));
		    geometries.add(sphere);
		    geometries.add(triangle);
		    scene.setListofGeom(geometries);
		    List<LightSource> lights = new ArrayList<LightSource>();
		    lights.add(new SpotLight(new Color(200,200,200) ,new Point3D(20,20,-60), 1,0,0, new Vector(0,0,1)));
		    scene.setLights(lights);
		    ImageWriter imageWriter = new ImageWriter("shadow test4", 500, 500, 500, 500);
		    Render render = new Render(scene, imageWriter);
		    render.renderImage();
		    render.printGrid(50);
		    render.getImagewriter().writeToimage();
	}
	
	
}

	
	

