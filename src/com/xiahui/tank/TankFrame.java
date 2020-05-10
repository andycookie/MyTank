package com.xiahui.tank;

import com.xiahui.tank.abstractfactory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/4/21
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class TankFrame extends Frame {

	private Tank tank = new Tank(200, 400, Dir.UP, this, Group.GOOD, false);
	public List<BaseBullet> bullets = new ArrayList<BaseBullet>();
	public List<BaseTank> tanks = new ArrayList<BaseTank>();
	public List<BaseExplode> explodes = new ArrayList<BaseExplode>();
	public GameFactory gameFactory = new DefaultFactory();
//	public GameFactory gameFactory = new RectFactory();
	public static final int GAME_WIDTH = 1000, GAME_HEIGHT = 800;

	public TankFrame() {
		setSize(GAME_WIDTH, GAME_HEIGHT);
		setResizable(false);
		setTitle("tank war");
		setVisible(true);
		this.addKeyListener(new MykeyListener());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics g) {
		Color blackColor = g.getColor();
		g.setColor(Color.white);
		g.setFont(new Font("宋体", Font.BOLD, 18));
		g.drawString("剩余的子弹数目:" + bullets.size(), 20, 90);
		g.drawString("剩余的敌方坦克数目:" + tanks.size(), 20, 110);
		g.drawString("爆炸的数目:" + explodes.size(), 20, 130);

		g.setColor(blackColor);
		g.setColor(Color.YELLOW);
		tank.paint(g);
		g.setColor(blackColor);

		for (int i = 0; i < bullets.size(); i++) {
			bullets.get(i).paint(g);
		}

		for (int i = 0; i < tanks.size(); i++) {
			tanks.get(i).paint(g);
		}
		for (int i = 0; i < explodes.size(); i++) {
			explodes.get(i).paint(g);
		}
		//碰撞检测
		for (int i = 0; i < bullets.size(); i++) {
			for (int j = 0; j < tanks.size(); j++) {
				bullets.get(i).collideWith(tanks.get(j));
			}
		}

//		if (explode.step == 0){
//			new Audio("audio/explode.wav").start();
//		}

//		for(Iterator<Bullet> iterator = bullets.iterator();iterator.hasNext();){
//			Bullet bullet = iterator.next();
//			if (!bullet.living){
//				bullets.remove(bullet);
//			}
//		}
	}

	Image offScreenImage = null;

	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
		}
		Graphics gOffScreen = offScreenImage.getGraphics();
		Color c = gOffScreen.getColor();
		gOffScreen.setColor(Color.BLACK);
		gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
		gOffScreen.setColor(c);
		paint(gOffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	private class MykeyListener extends KeyAdapter {

		boolean bU = false;
		boolean bD = false;
		boolean bL = false;
		boolean bR = false;

		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_UP:
					bU = true;
					break;
				case KeyEvent.VK_DOWN:
					bD = true;
					break;
				case KeyEvent.VK_LEFT:
					bL = true;
					break;
				case KeyEvent.VK_RIGHT:
					bR = true;
					break;
				default:
					System.out.println("按下...");
			}
			setMainTankDir();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			int key = e.getKeyCode();
			switch (key) {
				case KeyEvent.VK_UP:
					bU = false;
					break;
				case KeyEvent.VK_DOWN:
					bD = false;
					break;
				case KeyEvent.VK_LEFT:
					bL = false;
					break;
				case KeyEvent.VK_RIGHT:
					bR = false;
					break;
				case KeyEvent.VK_CONTROL:
					tank.fire();
					break;
				default:
					System.out.println("抬起...");
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			if (!bU && !bD && !bL && !bR) {
				tank.setMoving(false);
			} else {
				tank.setMoving(true);
				if (bU) tank.setDir(Dir.UP);
				if (bD) tank.setDir(Dir.DOWN);
				if (bL) tank.setDir(Dir.LEFT);
				if (bR) tank.setDir(Dir.RIGHT);
			}
		}
	}
}
