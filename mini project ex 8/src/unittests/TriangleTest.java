package unittests;
import geometries.*;
import elements.Camera;
import primitives.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Test;

public class TriangleTest {

	@Test
	public void TriangleTest1() {
		final int Width = 3;
		final int Height = 3;
		Ray[][] rays = new Ray [Height][Width];
	    Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
		new Vector (0.0, 1.0, 0.0),
		new Vector (0.0, 0.0, -1.0));
		Triangle triangle = new Triangle(new Point3D( 0, 1, -2),
		new Point3D( 1, -1, -2),
		new Point3D(-1, -1, -2),new Color(255,255,255),new Material(0,0,0,0,0));
		

		List<Point3D> list_intersection1 = new ArrayList<Point3D>();
		System.out.println("Camera:\n" + camera);
		for (int i = 0; i < Height; i++)
		{
		for (int j = 0; j < Width; j++)
		{
		rays[i][j] = camera.constructRayThroughPixel(Width, Height, j, i, 1, 3 * Width, 3 * Height);
		if((triangle. findIntersections(rays[i][j]))!=null){
		Map<Geometry,List<Point3D>> rayIntersectionPoints=new HashMap<Geometry,List<Point3D>> ();
		rayIntersectionPoints.putAll(triangle. findIntersections(rays[i][j]));
		for(Entry<Geometry, List<Point3D>> s: rayIntersectionPoints.entrySet())
		{
			list_intersection1.addAll(s.getValue());
		}
		}
		/*ArrayList<Point3D> rayIntersectionPoints = triangle. findIntersections(rays[i][j]);
		for (Point3D iPoint: rayIntersectionPoints)
			list_intersection1.add(iPoint);
			*/

		}
		}
		assertTrue(list_intersection1.size() == 1);
		System.out.println("Intersection Points:");
		for (Point3D iPoint: list_intersection1)
		System.out.println(iPoint);
		System.out.println("--");

		}
	@Test
	public void TriangleTest2()
	{
		final int Width = 3;
		final int Height = 3;
		Ray[][] rays = new Ray [Height][Width];
	    Camera camera = new Camera(new Point3D(0.0 ,0.0 ,0.0),
		new Vector (0.0, 1.0, 0.0),
		new Vector (0.0, 0.0, -1.0));
	    Triangle triangle2 = new Triangle(new Point3D( 0, 10, -2),
	    		new Point3D( 1, -1, -2),
	    		new Point3D(-1, -1, -2),new Color(255,255,255),new Material(0,0,0,0,0));
	    List<Point3D> list_intersection2 = new ArrayList<Point3D>();
	    System.out.println("Camera:\n" + camera);
		for (int i = 0; i < Height; i++)
		{
		for (int j = 0; j < Width; j++)
		{
		rays[i][j] = camera.constructRayThroughPixel(Width, Height, j, i, 1, 3 * Width, 3 * Height);
		if(triangle2.findIntersections(rays[i][j])!=null){
			Map<Geometry,List<Point3D>> rayIntersectionPoints2=new HashMap<Geometry,List<Point3D>> ();
			rayIntersectionPoints2.putAll(triangle2. findIntersections(rays[i][j]));
			for(Entry<Geometry, List<Point3D>> s: rayIntersectionPoints2.entrySet())
			{
				list_intersection2.addAll(s.getValue());
			}
		}
		/*ArrayList<Point3D> rayIntersectionPoints2 = triangle2.findIntersections(rays[i][j]);
		for (Point3D iPoint: rayIntersectionPoints2)
			list_intersection2.add(iPoint);*/

		}
		}
		assertTrue(list_intersection2.size() == 2);
		System.out.println("Intersection Points:");
		for (Point3D iPoint: list_intersection2)
			System.out.println(iPoint);

	}
		
	}


