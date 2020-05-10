package com.xiahui.tank.abstractfactory;


import com.xiahui.tank.*;

public class DefaultFactory extends GameFactory {

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf, boolean moving) {
		return new Tank(x, y, dir, tf, group, moving);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tf) {
		return new Explode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new Bullet(x, y, dir, tf, group);
	}

}
