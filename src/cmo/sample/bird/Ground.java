package cmo.sample.bird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * ����
 */
public class Ground {
	// �����X����
	private int x;
	// �����Y����
	private int y;
	// ����Ŀ��
	private int width;
	// ����ĸ߶�
	private int height;
	// ����ͼƬ
	private BufferedImage groundImage;

	public Ground() throws IOException {
		x = 0;
		y = 500;
		groundImage = ImageIO.read(getClass().getResource("ground.png"));
		width = groundImage.getWidth();
		height = groundImage.getHeight();
	}

	// �����ƶ�
	public void step() {
		// ���������ƶ�
		x--;
		if (x == -109) {
			// ���õ���λ��
			x = 0;
		}
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
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
	 * @return the background
	 */
	public BufferedImage getGroundImage() {
		return groundImage;
	}

}
