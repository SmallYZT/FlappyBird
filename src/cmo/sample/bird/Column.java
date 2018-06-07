package cmo.sample.bird;
/*
 * 柱子
 */

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Column {
	// 柱子中心点X坐标
	private int x;
	// 柱子中心点Y坐标
	private int y;
	// 柱子的宽度
	private int width;
	// 柱子的高度
	private int height;
	// 上下柱子的间距
	private int gap;
	// 相邻柱子的间距
	private int distance;
	// 背景图片
	private BufferedImage columnImage;
	// 随机数字类
	Random random = new Random();

	public Column() {
		super();
	}

	// n表示第几根柱子
	public Column(int n) throws IOException {
		columnImage = ImageIO.read(getClass().getResource("column.png"));
		width = columnImage.getWidth();
		height = columnImage.getHeight();
		gap = 144;
		distance = 245;
		x = 550 + (n - 1) * distance;
		y = random.nextInt(218) + 132;
	}

	// 柱子移动
	public void step() {
		// 柱子向左移动
		x--;
		if (x == (-width / 2)) {
			// 柱子回到初始位置
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
