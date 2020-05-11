package com.xiahui.tank;

import com.xiahui.tank.abstractfactory.BaseBullet;
import com.xiahui.tank.abstractfactory.BaseTank;

import java.awt.*;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/4/22
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class Bullet extends BaseBullet{
	private int x = 300, y = 300;
	private Dir dir = Dir.DOWN;
	private static final int SPEED = 8;

	private TankFrame tankFrame = null;
	private Group group = Group.BAD;

	public Bullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
		switch (dir) {
			case UP:
				BULLET_WIDTH = ResourceMgr.bulletU.getWidth();
				BULLET_HEIGTH = ResourceMgr.bulletU.getHeight();
				x = x + ResourceMgr.goodTankU.getWidth() / 2 - BULLET_WIDTH / 2;
				y = y - BULLET_HEIGTH / 2;
				break;
			case DOWN:
				BULLET_WIDTH = ResourceMgr.bulletD.getWidth();
				BULLET_HEIGTH = ResourceMgr.bulletD.getHeight();
				x = x + ResourceMgr.goodTankD.getWidth() / 2 - BULLET_WIDTH / 2;
				y = y + ResourceMgr.goodTankD.getHeight() - BULLET_HEIGTH / 2;
				break;
			case LEFT:
				BULLET_WIDTH = ResourceMgr.bulletL.getWidth();
				BULLET_HEIGTH = ResourceMgr.bulletL.getHeight();
				x = x - BULLET_WIDTH / 2;
				y = y + ResourceMgr.goodTankL.getHeight() / 2 - BULLET_HEIGTH / 2;
				break;
			case RIGHT:
				BULLET_WIDTH = ResourceMgr.bulletR.getWidth();
				BULLET_HEIGTH = ResourceMgr.bulletR.getHeight();
				x = x + ResourceMgr.goodTankR.getWidth() - BULLET_WIDTH / 2;
				y = y + ResourceMgr.goodTankR.getHeight() / 2 - BULLET_HEIGTH / 2;
				break;
		}
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tankFrame = tankFrame;
		this.group = group;

		rect.x = this.x;
		rect.y = this.y;
		rect.width = this.BULLET_WIDTH;
		rect.height = this.BULLET_HEIGTH;

		this.tankFrame.bullets.add(this);
	}

	public Dir getDir() {
		return dir;
	}

	public void setDir(Dir dir) {
		this.dir = dir;
	}

	public void paint(Graphics g) {
		if (!living) {
			tankFrame.bullets.remove(this);
		}
		switch (dir) {
			case LEFT:
				g.drawImage(ResourceMgr.bulletL, x, y, null);
				break;
			case RIGHT:
				g.drawImage(ResourceMgr.bulletR, x, y, null);
				break;
			case UP:
				g.drawImage(ResourceMgr.bulletU, x, y, null);
				break;
			case DOWN:
				g.drawImage(ResourceMgr.bulletD, x, y, null);
				break;
		}
		move();
	}


	public void move() {
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
				break;
		}

		rect.x = this.x;
		rect.y = this.y;
		rect.width = this.BULLET_WIDTH;
		rect.height = this.BULLET_HEIGTH;

		if (x < 0 || y < 0 || x > tankFrame.GAME_WIDTH || y > tankFrame.GAME_HEIGHT) {
			this.living = false;
		}
	}


	public void collideWith(BaseTank tank) {
		if (this.group == tank.getGroup())  return;
		//碰撞检测优化
		if (rect.intersects(tank.rect)) {
			this.living = false;
			tank.living = false;
			tankFrame.explodes.add(tankFrame.gameFactory.createExplode(tank.getX(), tank.getY(), tankFrame));
		}
	}
}
