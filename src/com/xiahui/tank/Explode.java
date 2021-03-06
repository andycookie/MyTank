package com.xiahui.tank;

import java.awt.*;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/3
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class Explode extends GameObject {
	private int x, y;
	private GameModel gameModel;
	public int step = 0;
	public int EXPLODE_WIDTH = ResourceMgr.fireImages[0].getWidth();
	public int EXPLODE_HEIGHT = ResourceMgr.fireImages[0].getHeight();

	public Explode(int x, int y, GameModel gameModel) {
		x = x + ResourceMgr.goodTankU.getWidth() / 2 - EXPLODE_WIDTH / 2;
		y = y + ResourceMgr.goodTankU.getHeight() / 2 - EXPLODE_HEIGHT / 2;
		this.x = x;
		this.y = y;
		this.gameModel = gameModel;
	}

	public void paint(Graphics g) {
		g.drawImage(ResourceMgr.fireImages[step++], x, y, null);
		if (step == ResourceMgr.fireImages.length) {
			gameModel.gameObjects.remove(this);
		}
	}
}
