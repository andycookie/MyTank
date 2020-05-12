package com.xiahui.tank;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/5/4
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class FourFireStrategy implements FireStrategy {

	@Override
	public void fire(Tank tank) {
		Dir[] values = Dir.values();
		for (Dir dir : values) {
			new Bullet(tank.x, tank.y, dir, tank.gameModel, tank.group);
		}
	}
}
