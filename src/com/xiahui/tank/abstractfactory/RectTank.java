package com.xiahui.tank.abstractfactory;


import com.xiahui.tank.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {

	private static final int SPEED = 2;
	private Random random = new Random();
	int x, y;
	Dir dir = Dir.DOWN;
	private boolean moving = true;
	TankFrame tf = null;


	public RectTank(int x, int y, Dir dir, Group group, TankFrame tf, boolean moving) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
		this.group = group;
		this.moving = moving;

		rect.x = this.x;
		rect.y = this.y;
		rect.width = TANK_WIDTH;
		rect.height = TANK_HEIGHT;

	}

	public void fire() {
		Dir[] dirs = Dir.values();
		for (Dir dir : dirs) {
			tf.gameFactory.createBullet(this.x, this.y, dir, group, tf);
		}

		if (group == Group.GOOD)
			new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
	}

	public Dir getDir() {
		return dir;
	}

	public int getX() {
		return x;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getY() {
		return y;
	}

	public boolean isMoving() {
		return moving;
	}


	@SuppressWarnings("all")
	private void move() {
		if (!moving)
			return;

		switch (dir) {
			case LEFT:
				x -= SPEED;
				break;
			case UP:
				y -= SPEED;
				break;
			case RIGHT:
				x += SPEED;
				break;
			case DOWN:
				y += SPEED;
				break;
		}

		if (this.group == Group.BAD && random.nextInt(100) > 95)
			this.fire();

		if (this.group == Group.BAD && random.nextInt(100) > 95)
			randomDir();

		boundsCheck();
		// update rect
		rect.x = this.x;
		rect.y = this.y;
		rect.width = TANK_WIDTH;
		rect.height = TANK_HEIGHT;

	}

	private void boundsCheck() {
		if (this.x < 2)
			x = 2;
		if (this.y < 28)
			y = 28;
		if (this.x > TankFrame.GAME_WIDTH - this.TANK_WIDTH - 2)
			x = TankFrame.GAME_WIDTH - this.TANK_WIDTH - 2;
		if (this.y > TankFrame.GAME_HEIGHT - this.TANK_HEIGHT - 2)
			y = TankFrame.GAME_HEIGHT - this.TANK_HEIGHT - 2;
	}

	private void randomDir() {

		this.dir = Dir.values()[random.nextInt(4)];
	}

	@SuppressWarnings("all")
	public void paint(Graphics g) {
		if (!living){
			tf.tanks.remove(this);
		}

		Color c = g.getColor();
		g.setColor(group == Group.GOOD ? Color.RED : Color.BLUE);

		switch (dir) {
			case LEFT:
				TANK_WIDTH = ResourceMgr.goodTankL.getWidth();
				TANK_HEIGHT = ResourceMgr.goodTankL.getHeight();
				break;
			case RIGHT:
				TANK_WIDTH = ResourceMgr.goodTankR.getWidth();
				TANK_HEIGHT = ResourceMgr.goodTankR.getHeight();
				break;
			case UP:
				TANK_WIDTH = ResourceMgr.goodTankU.getWidth();
				TANK_HEIGHT = ResourceMgr.goodTankU.getHeight();
				break;
			case DOWN:
				TANK_WIDTH = ResourceMgr.goodTankD.getWidth();
				TANK_HEIGHT = ResourceMgr.goodTankD.getHeight();
				break;
		}
		g.fillRect(x, y, TANK_WIDTH, TANK_HEIGHT);
		g.setColor(c);
		move();

	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}


}
