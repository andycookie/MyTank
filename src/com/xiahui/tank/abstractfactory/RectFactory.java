package com.xiahui.tank.abstractfactory;


import com.xiahui.tank.Dir;
import com.xiahui.tank.Group;
import com.xiahui.tank.TankFrame;

public class RectFactory extends GameFactory {

	@Override
	public BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf, boolean moving) {
		return new RectTank(x, y, dir, group, tf, moving);
	}

	@Override
	public BaseExplode createExplode(int x, int y, TankFrame tf) {
		return new RectExplode(x, y, tf);
	}

	@Override
	public BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf) {
		return new RectBullet(x, y, dir, tf, group);
	}

}
