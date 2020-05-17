package com.xiahui.tank;

import com.xiahui.tank.collider.ColliderChain;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: http://www.pingan.com
 * @Date: 2020/5/12
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class GameModel {

	private Tank tank = new Tank(200, 400, Dir.UP, this, Group.GOOD, false);

	List<GameObject> gameObjects = new ArrayList<>();

	ColliderChain colliderChain = new ColliderChain();

	public GameModel() {
		gameObjects.add(tank);

		int initTankCount = Integer.parseInt((String) PropertyMsr.getValue("initTankCount"));
		//初始化敌方坦克
		for (int i = 0; i < initTankCount; i++) {
			add(new Tank(100 + i * 90, 200, Dir.DOWN, this, Group.BAD, true));
		}
	}

	public void paint(Graphics g) {
		Color blackColor = g.getColor();
		g.setColor(Color.white);
		g.setFont(new Font("宋体", Font.BOLD, 18));
//		g.drawString("剩余的子弹数目:" + bullets.size(), 20, 90);
//		g.drawString("剩余的敌方坦克数目:" + tanks.size(), 20, 110);
//		g.drawString("爆炸的数目:" + explodes.size(), 20, 130);

		g.setColor(blackColor);
		Color color = g.getColor();
		g.setColor(Color.YELLOW);
		//tank.paint(g);


		for (int i = 0; i < gameObjects.size(); i++) {
			gameObjects.get(i).paint(g);
		}
		g.setColor(color);

		//碰撞检测
		for (int i = 0; i < gameObjects.size(); i++) {
			for (int j = i + 1; j < gameObjects.size(); j++) {
				colliderChain.collideWith(gameObjects.get(i),gameObjects.get(j));
			}
		}
	}

	public Tank getMainTank() {
		return tank;
	}

	public void add(GameObject gameObject) {
		gameObjects.add(gameObject);
	}

	public void remove(GameObject gameObject) {
		gameObjects.remove(gameObject);
	}
}
