package com.xiahui.tank;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/4/21
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class Main {
	public static void main(String[] arr) {

		TankFrame tankFrame = new TankFrame();

		while (true) {
			try {
				Thread.sleep(50);
				tankFrame.repaint();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
