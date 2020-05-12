package com.xiahui.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Auther: http://www.maisui.com
 * @Date: 2020/4/21
 * @Description: com.xiahui.tank
 * @version: 1.0
 */
public class TankFrame extends Frame {

	GameModel gameModel = new GameModel();

	static final int GAME_WIDTH = 1000, GAME_HEIGHT = 800;

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
		gameModel.paint(g);
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
					gameModel.getMainTank().fire();
					break;
				default:
					System.out.println("抬起...");
			}
			setMainTankDir();
		}

		private void setMainTankDir() {
			Tank tank = gameModel.getMainTank();
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
