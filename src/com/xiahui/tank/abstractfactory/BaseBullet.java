package com.xiahui.tank.abstractfactory;

import com.xiahui.tank.ResourceMgr;

import java.awt.*;

public abstract class BaseBullet {
	public boolean living = true;
	public Rectangle rect = new Rectangle();
	public int BULLET_WIDTH = ResourceMgr.bulletU.getWidth();
	public int BULLET_HEIGTH = ResourceMgr.bulletU.getWidth();

	public abstract void paint(Graphics g);

	public abstract void collideWith(BaseTank tank);
}
