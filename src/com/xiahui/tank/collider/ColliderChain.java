package com.xiahui.tank.collider;

import com.xiahui.tank.GameObject;

import java.util.LinkedList;
import java.util.List;

/**
 * @Auther: http://www.pingan.com
 * @Date: 2020/5/13
 * @Description: com.xiahui.tank.collider
 * @version: 1.0
 */
public class ColliderChain implements Collider{

	public List<Collider> chains = new LinkedList<>();

	public ColliderChain() {
		chains.add(new BulletTankCollider());
		chains.add(new TankTankCollider());
	}

	public void add(Collider collider) {
		chains.add(collider);
	}

	/**
	 * true:碰撞  撞了就结束循环
	 * fasle:未碰撞
	 *
	 * @param o1
	 * @param o2
	 * @return
	 */
	@Override
	public boolean collideWith(GameObject o1, GameObject o2) {
		for (int i = 0; i < chains.size(); i++) {
			if (chains.get(i).collideWith(o1, o2)) {
				return true;
			}
		}
		return false;
	}
}
