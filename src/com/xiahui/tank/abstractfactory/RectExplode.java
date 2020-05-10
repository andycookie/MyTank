package com.xiahui.tank.abstractfactory;

import com.xiahui.tank.Audio;
import com.xiahui.tank.ResourceMgr;
import com.xiahui.tank.TankFrame;

import java.awt.*;

public class RectExplode extends BaseExplode {
	private int x, y;
	TankFrame tf = null;
	private int step = 0;
	
	public RectExplode(int x, int y, TankFrame tf) {
		x = x + ResourceMgr.goodTankU.getWidth() / 2 - EXPLODE_WIDTH / 2;
		y = y + ResourceMgr.goodTankU.getHeight() / 2 - EXPLODE_HEIGHT / 2;
		this.x = x;
		this.y = y;
		this.tf = tf;
		
		new Thread(()->new Audio("audio/explode.wav").play()).start();
	}
	
	
	@Override
	public void paint(Graphics g) {
		
		//g.drawImage(ResourceMgr.explodes[step++], x, y, null);
		Color c = g.getColor();
		g.setColor(Color.RED);
		g.fillRect(x, y, 10*step, 10*step);
		step++;
		
		if(step >= 15){
			tf.explodes.remove(this);
		}

		g.setColor(c);
	}

}
