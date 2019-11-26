package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import primitives.*;
import geometries.*;
import elements.*;
import org.junit.Test;

public class PlaneTest {

	@Test
	public void testIntersectionPlane(){
		final int WIDTH = 3;
		final int HEIGHT = 3;
		Ray[][] rays = new Ray [HEIGHT][WIDTH];
		Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
		 new Vector (0.0, 1.0, 0.0),
		 new Vector (0.0, 0.0, -1.0));
		Plane plane = new Plane(new Point3D(0.0, 0.0, -3.0),new Vector(0.0, 0.0, -1.0),new Color(255,255,255),new Material(0,0,0,0,0));
		Plane plane2 = new Plane(new Point3D(0.0, 0.0, -3.0),new Vector(0.0, 0.25, -1.0),new Color(255,255,255),new Material(0,0,0,0,0));
		List<Point3D> intersectionPointsPlane = new ArrayList<Point3D>();
		List<Point3D> intersectionPointsPlane2 = new ArrayList<Point3D>();
		System.out.println("Camera:\n" + camera);
		for (int i = 0; i < HEIGHT; i++){
		for (int j = 0; j < WIDTH; j++){
		rays[i][j] = camera.constructRayThroughPixel(
		WIDTH, HEIGHT, j, i, 1, 3 * WIDTH, 3 * HEIGHT);
		Map<Geometry,List<Point3D>> rayIntersectionPoints=new HashMap<Geometry,List<Point3D>> ();
		Map<Geometry,List<Point3D>> rayIntersectionPoints2=new HashMap<Geometry,List<Point3D>> ();
		rayIntersectionPoints.putAll(plane.findIntersections(rays[i][j]));
		rayIntersectionPoints2.putAll(plane2.findIntersections(rays[i][j]));
		//ArrayList<Point3D> rayIntersectionPoints2 = plane2.findIntersections(rays[i][j]);
		for(Entry<Geometry, List<Point3D>> s: rayIntersectionPoints.entrySet())
		{
			intersectionPointsPlane.addAll(s.getValue());
		}
		for(Entry<Geometry, List<Point3D>> s: rayIntersectionPoints2.entrySet())
		{
			intersectionPointsPlane2.addAll(s.getValue());
		}
		
		/*for (Point3D iPoint: rayIntersectionPoints)
		intersectionPointsPlane.add(iPoint);
		for (Point3D iPoint: rayIntersectionPoints2)
		intersectionPointsPlane2.add(iPoint);*/
		}
		}
		assertTrue(intersectionPointsPlane. size() == 9);
		assertTrue(intersectionPointsPlane2.size() == 9);
		for (Point3D iPoint: intersectionPointsPlane)
		System.out.println(iPoint);
		System.out.println("---");
		for (Point3D iPoint: intersectionPointsPlane2)
		System.out.println(iPoint);
		}
}


