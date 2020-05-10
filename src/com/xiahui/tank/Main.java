package com.xiahui.tank;

import com.xiahui.tank.abstractfactory.BaseTank;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/4/21
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class Main {
	public static void main(String[] arr) {
		TankFrame tankFrame = new TankFrame();
		int initTankCount = Integer.parseInt((String) PropertyMsr.getValue("initTankCount"));
		//初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			BaseTank tank = tankFrame.gameFactory.createTank(100 + i * 90, 200, Dir.DOWN, Group.BAD, tankFrame, true);
			tankFrame.tanks.add(tank);
		}

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
