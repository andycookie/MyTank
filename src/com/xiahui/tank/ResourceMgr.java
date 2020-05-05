package com.xiahui.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/2
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class ResourceMgr {
	public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
	public static BufferedImage badTankL, badTankU, badTankR, badTankD;
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	public static BufferedImage[] fireImages = new BufferedImage[16];

	static {
		try {
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);

			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);

			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);
			for (int i = 0; i < 16; i++) {
				fireImages[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images\\e" + (i+1) + ".gif"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
