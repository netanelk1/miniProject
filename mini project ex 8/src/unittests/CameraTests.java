package unittests;

import static org.junit.Assert.*;
import scene.*;

import org.junit.Test;
import primitives.*;
import elements.*;
public class CameraTests {

	@Test
	public void testRaysConstruction(){
		int Nx=3;
		int Ny=3;
		int i=3;
		int j=3;
		double screenDistance=100;
		double screeWidth=150;
		double screeHeight=150;
		Point3D p0=new Point3D(0,0,0);
		Vector Vto=new Vector(0,0,-1);
		Vector Vup=new Vector(0,-1,0);
	//Vector Vright=new Vector(1,0,0) לפי הכל
		Scene sc=new Scene("Scene test");
		Camera camer=new Camera(p0,Vup,Vto);
		sc.setCem(camer);
		Ray r=new Ray(sc.getCem().constructRayThroughPixel(Nx, Ny, i, j, screenDistance, screeWidth, screeHeight));
		System.out.println(r.toString());
		Vector ch=new Vector(1.0/Math.sqrt(6.0),1.0/Math.sqrt(6.0),-Math.sqrt(6.0)/3.0);
		Ray check=new Ray(p0,ch);
		assertTrue(r.getDirection().getHead().getX().equals(check.getDirection().getHead().getX()) && r.getDirection().getHead().getY().equals(check.getDirection().getHead().getY())&& r.getDirection().getHead().getZ().equals(check.getDirection().getHead().getZ()));
	}	
}
