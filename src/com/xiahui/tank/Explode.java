package com.xiahui.tank;

import java.awt.*;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/3
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class Explode {
	private int x, y;
	private TankFrame tankFrame;
	public int step = 0;
	public int EXPLODE_WIDTH = ResourceMgr.fireImages[0].getWidth();
	public int EXPLODE_HEIGHT = ResourceMgr.fireImages[0].getHeight();

	public Explode(int x, int y, TankFrame tankFrame) {
		x = x + ResourceMgr.goodTankU.getWidth() / 2 - EXPLODE_WIDTH / 2;
		y = y + ResourceMgr.goodTankU.getHeight() / 2 - EXPLODE_HEIGHT / 2;
		this.x = x;
		this.y = y;
		this.tankFrame = tankFrame;
	}

	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.fireImages[step++], x, y, null);
		if (step == ResourceMgr.fireImages.length) {
			tankFrame.explodes.remove(this);
		}
	}
}
