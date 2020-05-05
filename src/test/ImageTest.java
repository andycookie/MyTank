package test;


import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/2
 * @Description: test
 * @version: 1.0
 */
public class ImageTest {

	@Test
	public void test(){
		System.out.println("-------------");
		try {
			BufferedImage image = ImageIO.read(new File("C:\\Users\\Administrator\\Desktop\\1.jpg"));
			System.out.println(image);
			BufferedImage image1 = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("images\\bulletD.gif"));
			System.out.println(image1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
