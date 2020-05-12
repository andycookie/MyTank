package com.xiahui.tank;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/4
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class DefaultFireStrategy implements FireStrategy{

	@Override
	public void fire(Tank tank) {
		new Bullet(tank.x, tank.y, tank.dir, tank.gameModel, tank.group);
	}
}
