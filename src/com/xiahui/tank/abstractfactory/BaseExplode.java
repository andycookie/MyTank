package com.xiahui.tank.abstractfactory;

import com.xiahui.tank.ResourceMgr;

import java.awt.*;

public abstract class BaseExplode {
	public int EXPLODE_WIDTH = ResourceMgr.fireImages[0].getWidth();
	public int EXPLODE_HEIGHT = ResourceMgr.fireImages[0].getHeight();

	public abstract void paint(Graphics g);
}
