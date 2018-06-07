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
 * ��Ϸ
 */
public class BirdGame extends JPanel {
	// С��
	private Bird bird;
	// ����
	private Ground ground;
	// ��һ������
	private Column column1;
	// �ڶ�������
	private Column column2;
	// ����ͼƬ
	private BufferedImage bgImage;
	// ����
	private int score;
	// ��Ϸ��ʼ
	// private boolean gameOver;
	private BufferedImage gameOverImage;

	// ��Ϸ״̬
	private int state;
	private static final int START = 0;
	private static final int RUNNING = 1;
	private static final int GAME_OVER = 2;
	private BufferedImage startImage;

	// ��ʼ����Ϸ����
	public BirdGame() throws IOException {
		// ��ʼ��С��
		bird = new Bird();
		// ��ʼ����һ������
		column1 = new Column(1);
		// ��ʼ���ڶ�������
		column2 = new Column(2);
		// ��ʼ������
		ground = new Ground();
		// ��ʼ������ͼƬ
		bgImage = ImageIO.read(getClass().getResource("bg.png"));
		// ��ʼ����Ϸ����
		// gameOver = false;
		state = START;
		startImage = ImageIO.read(getClass().getResource("start.png"));
		gameOverImage = ImageIO.read(getClass().getResource("gameover.png"));
	}

	// ��дpaint()����������Ϸ����
	public void paint(Graphics g) {
		super.paint(g);
		// ���Ʊ���
		g.drawImage(bgImage, 0, 0, null);
		// ���Ƶ�һ������
		g.drawImage(column1.getColumnImage(), column1.getX() - column1.getWidth() / 2,
				column1.getY() - column1.getHeight() / 2, null);
		// ���Ƶڶ�������
		g.drawImage(column2.getColumnImage(), column2.getX() - column2.getWidth() / 2,
				column2.getY() - column2.getHeight() / 2, null);
		// ���Ƶ���
		g.drawImage(ground.getGroundImage(), ground.getX(), ground.getY(), null);
		// ����С��
		// ׷����ת����
		Graphics2D g2 = (Graphics2D) g;
		g2.rotate(-bird.getAlpha(), bird.getX(), bird.getY());
		g.drawImage(bird.getBirdImage(), bird.getX() - bird.getWidth() / 2, bird.getY() - bird.getHeight() / 2, null);
		g2.rotate(bird.getAlpha(), bird.getX(), bird.getY());

		// ���Ʒ���
		Font font = new Font(Font.SANS_SERIF, Font.BOLD, 40);
		g.setFont(font);
		g.drawString("" + score, 30, 60);
		// ������Ӱ
		g.setColor(Color.white);
		g.drawString("" + score, 27, 57);
		// ��Ϸ��ʼ��������
		switch (state) {
		case START:
			g.drawImage(startImage, 0, 0, null);
			break;
		case GAME_OVER:
			g.drawImage(gameOverImage, 0, 0, null);
			break;
		}

	}

	// �����˶�---����һϵ�н���Ԫ���ƶ�
	public void action() {
		new Thread() {
			public void run() {
				while (true) {
					switch (state) {
					case START:
						// С��Ӷ����
						bird.fly();
						// �����ƶ�
						ground.step();
						break;
					case RUNNING:
						// С��Ӷ����
						bird.fly();
						// �����ƶ�
						ground.step();
						// �����ƶ�
						column1.step();
						column2.step();
						// С���ƶ�
						bird.step();
						// С��Ӷ����
						bird.fly();
						break;
					}

					// �����������¼�
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
					// ���������¼����ص����
					addMouseListener(mouseListener);
					// ���ӼƷ�
					if (bird.getX() == column1.getX() || bird.getX() == column2.getX()) {
						score++;
					}
					// ��ײ���������
					if (bird.hit(ground) || bird.hit(column1) || bird.hit(column2)) {
						state = GAME_OVER;
					}
					// ÿִ��һ�ζ��������»��ƽ���
					repaint();
					// ���ʱ��ˢ��һ�Σ�һ��30�Σ�
					try {
						Thread.sleep(1000 / 30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	// ��Ϸ����
	public static void main(String[] args) throws IOException {
		// ��������
		JFrame frame = new JFrame("FlappyBird");
		// ������Ϸ���
		BirdGame birdGame = new BirdGame();
		// ���ý����˶�����
		birdGame.action();
		// �������ӵ�����
		frame.add(birdGame);
		// ���ô��ڴ�С
		frame.setSize(432, 644);
		// ���ô��ڹ̶���С
		frame.setResizable(false);
		// ���õ���ر�ʱ�˳�����
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ô��ڵ�λ�ã�����
		frame.setLocationRelativeTo(null);
		// ���ô��ڿɼ�
		frame.setVisible(true);
	}
}
