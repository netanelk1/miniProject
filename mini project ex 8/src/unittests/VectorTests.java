package unittests;

import static org.junit.Assert.*;
import primitives.*;

import org.junit.Test;

public class VectorTests {
	@Test
	public void test_add_vector(){
		Vector v1=new Vector(1,2,3);
		Vector v2=new Vector(4,5,6);
		Vector v3=new Vector(v1.add(v2));
		Vector v4=new Vector(5,7,9);
		assertEquals(v3,v4);
	}
	@Test
	public void test_sub_vector(){
		Vector v1=new Vector(4,5,6);
		Vector v2=new Vector(1,2,3);
		Vector v3=new Vector(v1.subtract(v2));
		Vector v4=new Vector(3,3,3);
		assertEquals(v3,v4);
	}
	@Test
	public void test_dot_product(){
		Vector v1=new Vector(1,2,3);
		Vector v2=new Vector(4,5,6);
		int num  =(int)(v1.dotProduct(v2));
		assertEquals(num,32);
	}
	@Test
	public void test_cross_product(){
		Vector v1=new Vector(1,2,3);
		Vector v2=new Vector(4,5,6);
		Vector v3=new Vector(v1.crossProduct(v2));
        Vector v4=new Vector(-3,6,-3);
		assertEquals(v3,v4);
	}
	@Test
	public void test_leght_vec(){
		Vector v1=new Vector(1,2,3);
	    double num= v1.lengthVec();
	    assertEquals(" ",num,Math.sqrt(14),1e-10);
	  
	}
	@Test
	public void test_normalization() {

		Vector v = new Vector(3.5,-5,10);
		v.normalization();
		assertEquals("",1, v.lengthVec(),1e-10);
	}
	@Test 
	public void test_scalar(){
		Vector v1=new Vector(1,2,3);
		double num=3;
		Vector v2=new Vector(v1.scalar(num));
		Vector v3=new Vector(3,6,9);
		assertEquals(v2,v3);
	}

}
