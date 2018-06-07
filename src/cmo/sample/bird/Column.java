package cmo.sample.bird;
/*
 * ����
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Column {
	// �������ĵ�X����
	private int x;
	// �������ĵ�Y����
	private int y;
	// ���ӵĿ��
	private int width;
	// ���ӵĸ߶�
	private int height;
	// �������ӵļ��
	private int gap;
	// �������ӵļ��
	private int distance;
	// ����ͼƬ
	private BufferedImage columnImage;
	// ���������
	Random random = new Random();

	public Column() {
		super();
	}

	// n��ʾ�ڼ�������
	public Column(int n) throws IOException {
		columnImage = ImageIO.read(getClass().getResource("column.png"));
		width = columnImage.getWidth();
		height = columnImage.getHeight();
		gap = 144;
		distance = 245;
		x = 550 + (n - 1) * distance;
		y = random.nextInt(218) + 132;
	}

	// �����ƶ�
	public void step() {
		// ���������ƶ�
		x--;
		if (x == (-width / 2)) {
			// ���ӻص���ʼλ��
			x = distance * 2 - (width / 2);
			y = random.nextInt(218) + 132;
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
	 * @return the gap
	 */
	public int getGap() {
		return gap;
	}

	/**
	 * @return the distance
	 */
	public int getDistance() {
		return distance;
	}

	/**
	 * @return the columnImage
	 */
	public BufferedImage getColumnImage() {
		return columnImage;
	}

}
