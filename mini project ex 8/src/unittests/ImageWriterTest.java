package unittests;

import static org.junit.Assert.*;
import renderer.*;

import org.junit.Test;

public class ImageWriterTest {

	@Test
	public void writeImage_test() {
		ImageWriter im=new ImageWriter("image test",500,500,500,500);
		for(int i=0;i<500;i++)
		{
			for(int j=0;j<500;j++)
			{
				if(i%50==0||j%50==0)
				{
					im.writePixel(i, j, 255,255,255);
				}
				else{
				im.writePixel(i, j,0,0,0);
				}
			}
		}
		im.writeToimage();
	}

}
