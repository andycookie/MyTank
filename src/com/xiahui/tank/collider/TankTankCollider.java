package com.xiahui.tank.collider;

import com.xiahui.tank.GameObject;
import com.xiahui.tank.Tank;

/**
 * @Auther: http://www.pingan.com
 * @Date: 2020/5/13
 * @Description: com.xiahui.tank.collider
 * @version: 1.0
 */
public class TankTankCollider implements Collider {

	@Override
	public boolean collideWith(GameObject o1, GameObject o2) {
		if (o1 instanceof Tank && o2 instanceof Tank) {
			return((Tank) o1).collideWith((Tank) o2);
		}
		return false;
	}
}
