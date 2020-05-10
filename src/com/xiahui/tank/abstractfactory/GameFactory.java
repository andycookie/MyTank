package com.xiahui.tank.abstractfactory;


import com.xiahui.tank.Dir;
import com.xiahui.tank.Group;
import com.xiahui.tank.TankFrame;

public abstract class GameFactory {
	public abstract BaseTank createTank(int x, int y, Dir dir, Group group, TankFrame tf, boolean moving);
	public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
	public abstract BaseBullet createBullet(int x, int y, Dir dir, Group group, TankFrame tf);
}
