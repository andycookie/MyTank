package com.xiahui.tank.collider;

import com.xiahui.tank.Bullet;
import com.xiahui.tank.GameObject;
import com.xiahui.tank.Tank;

/**
 * @Auther: http://www.pingan.com
 * @Date: 2020/5/13
 * @Description: com.xiahui.tank.collider
 * @version: 1.0
 */
public class BulletTankCollider implements Collider {

	@Override
	public boolean collideWith(GameObject o1, GameObject o2) {
		if (o1 instanceof Bullet && o2 instanceof Tank) {
			return ((Bullet) o1).collideWith((Tank)o2);
		} else if (o2 instanceof Bullet && o1 instanceof Tank) {
			return collideWith(o2, o1);
		}
		return false;
	}
}
