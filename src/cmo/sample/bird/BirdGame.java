package cmo.sample.bird;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * 游戏
 */
public class BirdGame extends JPanel {
	// 小鸟
	private Bird bird;
	// 地面
	private Ground ground;
	// 第一根柱子
	private Column column1;
	// 第二根柱子
	private Column column2;
	// 背景图片
	private BufferedImage bgImage;
	// 分数
	private int score;
	// 游戏开始
	// private boolean gameOver;
	private BufferedImage gameOverImage;

	// 游戏状态
	private int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int GAME_OVER = 2;
	private BufferedImage startImage;

	// 初始化游戏内容
	public BirdGame() throws IOException {
		// 初始化小鸟
		bird = new Bird();
		// 初始化第一根柱子
		column1 = new Column(1);
		// 初始化第二根柱子
		column2 = new Column(2);
		// 初始化地面
		ground = new Ground();
		// 初始化背景图片
		bgImage = ImageIO.read(getClass().getResource("bg.png"));
		// 初始化游戏结束
		// gameOver = false;
		state = START;
		startImage = ImageIO.read(getClass().getResource("start.png"));
		gameOverImage = ImageIO.read(getClass().getResource("gameover.png"));
	}

	// 重写paint()方法绘制游戏界面
	public void paint(Graphics g) {
		super.paint(g);
		// 绘制背景
		g.drawImage(bgImage, 0, 0, null);
		// 绘制第一根柱子
		g.drawImage(column1.getColumnImage(), column1.getX() - column1.getWidth() / 2,
				column1.getY() - column1.getHeight() / 2, null);
		// 绘制第二根柱子
		g.drawImage(column2.getColumnImage(), column2.getX() - column2.getWidth() / 2,
				column2.getY() - column2.getHeight() / 2, null);
		// 绘制地面
		g.drawImage(ground.getGroundImage(), ground.getX(), ground.getY(), null);
		// 绘制小鸟
		// 追加旋转动作
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.getAlpha(), bird.getX(), bird.getY());
		g.drawImage(bird.getBirdImage(), bird.getX() - bird.getWidth() / 2, bird.getY() - bird.getHeight() / 2, null);
		g2.rotate(bird.getAlpha(), bird.getX(), bird.getY());

		// 绘制分数
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(font);
		g.drawString("" + score, 30, 60);
		// 绘制重影
		g.setColor(Color.white);
		g.drawString("" + score, 27, 57);
		// 游戏开始结束画面
		switch (state) {
		case START:
			g.drawImage(startImage, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameOverImage, 0, 0, null);
			break;
		}

	}

	// 画面运动---控制一系列界面元素移动
	public void action() {
		new Thread() {
			public void run() {
				while (true) {
					switch (state) {
					case START:
						// 小鸟挥动翅膀
						bird.fly();
						// 地面移动
						ground.step();
						break;
					case RUNNING:
						// 小鸟挥动翅膀
						bird.fly();
						// 地面移动
						ground.step();
						// 柱子移动
						column1.step();
						column2.step();
						// 小鸟移动
						bird.step();
						// 小鸟挥动翅膀
						bird.fly();
						break;
					}

					// 增加鼠标监听事件
					MouseListener mouseListener = new MouseAdapter() {
						@Override
						public void mousePressed(MouseEvent e) {
							// bird.flappy();
							try {
								switch (state) {
								case START:
									state = RUNNING;
									break;
								case GAME_OVER:
									bird = new Bird();
									column1 = new Column(1);
									column2 = new Column(2);
									score = 0;
									state = START;
									break;
								case RUNNING:
									bird.flappy();
									break;
								}
							} catch (Exception e2) {
								e2.printStackTrace();
							}
						}
					};
					// 将鼠标监听事件挂载到面板
					addMouseListener(mouseListener);
					// 增加计分
					if (bird.getX() == column1.getX() || bird.getX() == column2.getX()) {
						score++;
					}
					// 碰撞地面和柱子
					if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
						state = GAME_OVER;
					}
					// 每执行一次动作，重新绘制界面
					repaint();
					// 间隔时间刷新一次（一秒30次）
					try {
						Thread.sleep(1000 / 30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	// 游戏启动
	public static void main(String[] args) throws IOException {
		// 构建窗口
		JFrame frame = new JFrame("FlappyBird");
		// 构建游戏面板
		BirdGame birdGame = new BirdGame();
		// 调用界面运动方法
		birdGame.action();
		// 将面板添加到窗口
		frame.add(birdGame);
		// 设置窗口大小
		frame.setSize(432, 644);
		// 设置窗口固定大小
		frame.setResizable(false);
		// 设置点击关闭时退出程序
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// 设置窗口的位置：居中
		frame.setLocationRelativeTo(null);
		// 设置窗口可见
		frame.setVisible(true);
	}
}
