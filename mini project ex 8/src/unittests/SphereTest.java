package unittests;
import static org.junit.Assert.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import primitives.*;
import geometries.*;
import elements.Camera;

public class SphereTest {
	@Test
	public void Spheretest1() {
		final int Width = 3;
		final int Height = 3;
		Ray[][] rays = new Ray [Height][Width];
		Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),new Vector (0.0, -1.0, 0.0),new Vector (0.0, 0.0, -1.0));

		Sphere sphere = new Sphere(1, new Point3D(0.0, 0.0, -3.0),new Color(255,255,255),new Material(0,0,0,0,0));
		List<Point3D> intersectionPointsSphere = new ArrayList<Point3D>();
		System.out.println("Camera:\n" + camera);
		for (int i = 0; i < Height; i++){
		for (int j = 0; j < Width; j++)
		{
		rays[i][j] = camera.constructRayThroughPixel(Width, Height, i, j, 1, 3 * Width, 3 * Height);
		Map<Geometry,List<Point3D>> rayIntersectionPoints=new HashMap<Geometry,List<Point3D>> ();
		if(sphere.findIntersections(rays[i][j])!=null){
		rayIntersectionPoints.putAll(sphere. findIntersections(rays[i][j]));
//		ArrayList<Point3D> rayIntersectionPoints = sphere. findIntersections(rays[i][j]);
		/*for (Point3D iPoint: rayIntersectionPoints)
		intersectionPointsSphere.add(iPoint);*/
		for(Entry<Geometry, List<Point3D>> s: rayIntersectionPoints.entrySet())
		{
			intersectionPointsSphere.addAll(s.getValue());
		}
		/*RayIntersectionsPoints.forEach((k,v)->{
		 * List<Point3D> l=new ArrayList<Point3D>();
		 * l=RayIntersectionsPoints.get(K);
		 * intersectionspointsSphere.Addall(l);
		 */
		}
		}
		}
		assertTrue(intersectionPointsSphere. size() == 2);
		System.out.println("Intersection Points:");
		Point3D p5= new Point3D(0.0,0.0,-2.0);
		Point3D p6= new Point3D(0.0,0.0,-4.0);
		for (Point3D iPoint: intersectionPointsSphere)
		{
		assertTrue(iPoint.equals(p5) == true||iPoint.equals(p6) == true);
		System.out.println(iPoint);
		}
	
	}
/*	@Test
	public void Spheretest2()
	{
		final int Width = 3;
		final int Height = 3;
		Ray[][] rays = new Ray [Height][Width];
		Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),new Vector (0.0, -1.0, 0.0),new Vector (0.0, 0.0, -1.0));

		Sphere sphere = new Sphere(10, new Point3D(0.0, 0.0, -3.0));
		ArrayList<Point3D> intersectionPointsSphere = new ArrayList<Point3D>();
		System.out.println("Camera:\n" + camera);
		for (int i = 0; i < Height; i++){
		for (int j = 0; j < Width; j++)
		{
		rays[i][j] = camera.construct_Ray_pixel(Width, Height, j, i, 1, 3 * Width, 3 * Height);
		ArrayList<Point3D> rayIntersectionPoints = sphere.findIntersections(rays[i][j]);
		for (Point3D iPoint: rayIntersectionPoints)
		intersectionPointsSphere.add(iPoint);

		}
		}
		assertTrue(intersectionPointsSphere.size() == 9);//9 intersections
		Point3D P1= new Point3D(-6.13,-6.84, 6.59);
		Point3D P2= new Point3D(-6.52,-8.46,4.0);
		Point3D P3= new Point3D(-8.88,-6.23,5.16);
		Point3D P4=new Point3D(-5.52,-6.1,7.51);
		Point3D P5= new Point3D(-6.78,-7.94,4.75);
		Point3D P6= new Point3D (-4.66,-5.83,7.96);
		Point3D P7= new Point3D(-6.21,-7.33,6.0);
		Point3D P8= new Point3D(-5.84,-6.44,7.11);
		Point3D P9= new Point3D(-6.25, -7.59, 5.64);
		for (Point3D iPoint: intersectionPointsSphere)
		{
		assertTrue(iPoint.equals(P1) == true||iPoint.equals(P2) == true||iPoint.equals(P3) == true||iPoint.equals(P4) == true||iPoint.equals(P5) == true||iPoint.equals(P6) == true||iPoint.equals(P7) == true||iPoint.equals(P8) == true||iPoint.equals(P9) == true);
		System.out.println(iPoint);
	}
	}
	*/
	/*@Test
	public void Spheretest3()
	{
		final int Width = 3;
		final int Height = 3;
		Ray[][] rays = new Ray [Height][Width];
		Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),new Vector (0.0, -1.0, 0.0),new Vector (0.0, 0.0, -1.0));
		Sphere sphere3 =new Sphere(1, new Point3D(0.0,0.0,1.0));
		ArrayList<Point3D> intrsectionPointSphere3 =new ArrayList<Point3D>();
		System.out.println("Camera:\n" + camera);
		for (int i = 0; i < Height; i++){
		for (int j = 0; j < Width; j++)
		{
		rays[i][j] = camera.construct_Ray_pixel(Width, Height, j, i, 1, 3 * Width, 3 * Height);
		ArrayList<Point3D> rayIntersectionPoint3 = sphere3.findIntersections(rays[i][j]);
		for(Point3D iPoint: rayIntersectionPoint3){
			intrsectionPointSphere3.add(iPoint);
		}
		}
		}
		assertTrue(intrsectionPointSphere3.size() == 0);
		for (Point3D iPoint: intrsectionPointSphere3)
		{
		assertTrue(iPoint==null);
		}
	}*/
	
	
}

	
	
	
	
	
	

