package com.xiahui.tank.abstractfactory;

import com.xiahui.tank.*;

import java.awt.*;

public class RectBullet extends BaseBullet {
	private static final int SPEED = 6;
	private int x, y;
	private Dir dir;
	public TankFrame tf = null;
	private Group group = Group.BAD;

	public RectBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
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
		this.tf = tf;
		this.group = group;

		rect.x = this.x;
		rect.y = this.y;
		rect.width = this.BULLET_WIDTH;
		rect.height = this.BULLET_HEIGTH;

		tf.bullets.add(this);

	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public void paint(Graphics g) {
		if (!living) {
			tf.bullets.remove(this);
		}

		Color c = g.getColor();
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, BULLET_WIDTH, BULLET_HEIGTH);
		g.setColor(c);

		move();
	}

	@SuppressWarnings("all")
	private void move() {
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

		//update rect
		rect.x = this.x;
		rect.y = this.y;
		rect.width = this.BULLET_WIDTH;
		rect.height = this.BULLET_HEIGTH;

		if (x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT) living = false;

	}

	public void collideWith(BaseTank tank) {
		if (this.group == tank.getGroup()) return;

		if (rect.intersects(tank.rect)) {
			this.living = false;
			tank.living = false;
			tf.explodes.add(tf.gameFactory.createExplode(tank.getX(), tank.getY(), tf));
		}

	}

}
