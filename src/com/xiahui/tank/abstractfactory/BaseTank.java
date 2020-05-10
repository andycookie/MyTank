package com.xiahui.tank.abstractfactory;

import com.xiahui.tank.Group;
import com.xiahui.tank.ResourceMgr;

import java.awt.*;

public abstract class BaseTank {
	public Group group = Group.BAD;
	public Rectangle rect = new Rectangle();
	public boolean living = true;
	public int TANK_WIDTH = ResourceMgr.goodTankU.getWidth();
	public int TANK_HEIGHT = ResourceMgr.goodTankU.getHeight();

	public abstract void paint(Graphics g);

	public Group getGroup() {
		return this.group;
	}

	public abstract int getX();

	public abstract int getY();
}
