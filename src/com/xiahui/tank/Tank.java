package com.xiahui.tank;

import java.awt.*;
import java.util.Random;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/4/22
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class Tank extends GameObject {
	int x = 200, y = 400;
	int preX = x, preY = y;
	Dir dir = Dir.UP;
	final int SPEED = 5;
	//是否静止
	private boolean moving = true;
	private int Tank_Width = ResourceMgr.goodTankU.getWidth();
	private int Tank_Height = ResourceMgr.goodTankU.getHeight();

	public boolean live = true;
	private Random random = new Random();
	Group group = Group.BAD;

	public Rectangle rect = new Rectangle();

	GameModel gameModel;

	FireStrategy fireStrategy = new DefaultFireStrategy();

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getTank_Width() {
		return Tank_Width;
	}

	public void setTank_Width(int tank_Width) {
		Tank_Width = tank_Width;
	}

	public int getTank_Height() {
		return Tank_Height;
	}

	public void setTank_Height(int tank_Height) {
		Tank_Height = tank_Height;
	}

	public Tank(int x, int y, Dir dir, GameModel gameModel, Group group, boolean moving) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.gameModel = gameModel;
		this.group = group;
		this.moving = moving;

		rect.x = this.x;
		rect.y = this.y;
		/**
		 * 初始化后,如果没有移动,tank的width/height是不会变化的. 所以要有显示初始化值
		 */
		rect.width = Tank_Width;
		rect.height = Tank_Height;

		if (Group.GOOD == this.group) {
			String fourFireStrategy = String.valueOf(PropertyMsr.getValue("fourFireStrategy"));

			try {
				fireStrategy = (FireStrategy) Class.forName(fourFireStrategy).getDeclaredConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void paint(Graphics g) {
		if (!live) {
			gameModel.gameObjects.remove(this);
		}
		switch (dir) {
			case LEFT:
				Tank_Width = ResourceMgr.goodTankL.getWidth();
				Tank_Height = ResourceMgr.goodTankL.getHeight();
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
				break;
			case RIGHT:
				Tank_Width = ResourceMgr.goodTankR.getWidth();
				Tank_Height = ResourceMgr.goodTankR.getHeight();
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
				break;
			case UP:
				Tank_Width = ResourceMgr.goodTankU.getWidth();
				Tank_Height = ResourceMgr.goodTankU.getHeight();
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
				break;
			case DOWN:
				Tank_Width = ResourceMgr.goodTankD.getWidth();
				Tank_Height = ResourceMgr.goodTankD.getHeight();
				g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
				break;
		}
		if (moving) {
			move();
		}
	}

	public void move() {
		preX = x;
		preY = y;
		switch (dir) {
			case UP:
				y -= SPEED;
				break;
			case DOWN:
				y += SPEED;
				break;
			case LEFT:
				x -= SPEED;
				break;
			case RIGHT:
				x += SPEED;
				break;
			default:
				System.out.println("ending");
		}

		if (this.group == Group.BAD) {
			if (random.nextInt(100) > 95) {
				this.fire();
			}
			randomDir();
		}

		boundsCheck();
		//update rect
		rect.x = this.x;
		rect.y = this.y;
		rect.width = Tank_Width;
		rect.height = Tank_Height;
	}

	//边界检测
	private void boundsCheck() {
		if (this.x < 2) x = 2;
		else if (this.y < 28) y = 28;
		else if (this.x > TankFrame.GAME_WIDTH - Tank_Width - 2) x = TankFrame.GAME_WIDTH - Tank_Width - 2;
		else if (this.y > TankFrame.GAME_HEIGHT - Tank_Height - 2) y = TankFrame.GAME_HEIGHT - Tank_Height - 2;
	}

	private void randomDir() {
		this.dir = Dir.values()[random.nextInt(4)];
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void fire() {
		fireStrategy.fire(this);
	}

	public boolean collideWith(Tank tank) {
		if (rect.intersects(tank.rect)) {
			this.x = this.preX;
			this.y = this.preY;
			tank.x = tank.preX;
			tank.y = tank.preY;
			return true;
		}

		return false;
	}
}
