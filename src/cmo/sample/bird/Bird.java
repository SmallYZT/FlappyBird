package cmo.sample.bird;
/*
 * С��
 */

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
	// С������ĵ��X����
	private int x;
	// С������ĵ��Y����
	private int y;
	// С��Ŀ��
	private int width;
	// С��ĸ߶�
	private int height;
	// С��ĳߴ�,���ں�����ײ���
	private int size;
	// С��ı���ͼƬ
	private BufferedImage birdImage;
	// С������
	private double g; // �������ٶ�
	private double v0; // ��ʼ�ٶ�
	private double speed; // ��ǰ�ٶ�
	private double s; // ���׾���
	private double t; // ����ʱ��
	private double alpha; // ���
	// С����
	private BufferedImage[] images;
	private int index; // �������±�

	public Bird() throws IOException {
		x = 132;
		y = 280;
		birdImage = ImageIO.read(getClass().getResource("0.png"));
		width = birdImage.getWidth();
		height = birdImage.getHeight();
		size = 40;
		g = 4;
		v0 = 20;
		t = 0.25;
		s = 0;
		speed = v0;
		alpha = 0;
		images = new BufferedImage[8];
		for (int i = 0; i < 8; i++) {
			images[i] = ImageIO.read(getClass().getResource(i + ".png"));
		}
	}

	// С�������˶�
	public void step() {
		double v0 = speed;
		s = v0 * t + g * t * t / 2; // ���������ƶ��ľ���
		y = y - (int) s; // �����������λ��
		double v = v0 - g * t; // �����½����ٶ�
		speed = v;
		// �������
		alpha = Math.atan(s / 8);
	}

	// С��Ӷ����
	public void fly() {
		index++;
		birdImage = images[(index / 12) % 8];
	}

	// ���������ٶ�
	public void flappy() {
		// ����ǰ�ٶ�����Ϊ��ʼ�ٶ�
		speed = v0;
	}

	// С����ײ����
	public boolean hit(Ground ground) {
		boolean hit = (y + size / 2) > ground.getY();
		if (hit) {
			// ������õ�������
			y = ground.getY() - size / 2;
			// ʹ��ˤ���ڵ�������ˤ��Ч��
			alpha = -3.14159265358979323 / 2;
		}
		return hit;
	}

	// С����ײ�ܵ�
	public boolean hit(Column column) {
		// �ȼ���Ƿ������ӷ�Χ����
		if (x > column.getX() - column.getWidth() / 2 - size / 2
				&& x < column.getX() + column.getWidth() / 2 + size / 2) {
			// ����Ƿ������ӷ�϶��
			if (y > column.getY() - column.getGap() / 2 + size / 2
					&& y < column.getY() + column.getGap() / 2 - size / 2) {
				return false;
			}
			return true;
		}
		return false;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @return the alpha
	 */
	public double getAlpha() {
		return alpha;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @return the birdImage
	 */
	public BufferedImage getBirdImage() {
		return birdImage;
	}

}
