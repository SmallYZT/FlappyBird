package cmo.sample.bird;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

/*
 * 地面
 */
public class Ground {
	// 地面的X坐标
	private int x;
	// 地面的Y坐标
	private int y;
	// 地面的宽度
	private int width;
	// 地面的高度
	private int height;
	// 背景图片
	private BufferedImage groundImage;

	public Ground() throws IOException {
		x = 0;
		y = 500;
		groundImage = ImageIO.read(getClass().getResource("ground.png"));
		width = groundImage.getWidth();
		height = groundImage.getHeight();
	}

	// 地面移动
	public void step() {
		// 地面向左移动
		x--;
		if (x == -109) {
			// 重置地面位置
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
