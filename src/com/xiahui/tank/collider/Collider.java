package com.xiahui.tank.collider;

import com.xiahui.tank.GameObject;

/**
 * @Auther: http://www.pingan.com
 * @Date: 2020/5/13
 * @Description: com.xiahui.tank.collider
 * @version: 1.0
 */
public interface Collider {
	public abstract boolean collideWith(GameObject o1, GameObject o2);
}
