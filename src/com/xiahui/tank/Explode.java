package com.xiahui.tank;

import com.xiahui.tank.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/3
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class Explode extends BaseExplode{
	private int x, y;
	private TankFrame tankFrame;
	public int step = 0;

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
