package cmo.sample.bird;
/*
 * 小鸟
 */

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bird {
	// 小鸟的中心点的X坐标
	private int x;
	// 小鸟的中心点的Y坐标
	private int y;
	// 小鸟的宽度
	private int width;
	// 小鸟的高度
	private int height;
	// 小鸟的尺寸,用于后续碰撞检测
	private int size;
	// 小鸟的背景图片
	private BufferedImage birdImage;
	// 小鸟上抛
	private double g; // 重力加速度
	private double v0; // 初始速度
	private double speed; // 当前速度
	private double s; // 上抛距离
	private double t; // 上抛时间
	private double alpha; // 倾角
	// 小鸟翅膀
	private BufferedImage[] images;
	private int index; // 动画祯下标

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

	// 小鸟上抛运动
	public void step() {
		double v0 = speed;
		s = v0 * t + g * t * t / 2; // 计算上抛移动的距离
		y = y - (int) s; // 计算鸟的坐标位置
		double v = v0 - g * t; // 计算下降的速度
		speed = v;
		// 计算倾角
		alpha = Math.atan(s / 8);
	}

	// 小鸟挥动翅膀
	public void fly() {
		index++;
		birdImage = images[(index / 12) % 8];
	}

	// 重置上抛速度
	public void flappy() {
		// 将当前速度重置为初始速度
		speed = v0;
	}

	// 小鸟碰撞地面
	public boolean hit(Ground ground) {
		boolean hit = (y + size / 2) > ground.getY();
		if (hit) {
			// 将鸟放置到地面上
			y = ground.getY() - size / 2;
			// 使鸟摔倒在地面上有摔倒效果
			alpha = -3.14159265358979323 / 2;
		}
		return hit;
	}

	// 小鸟碰撞管道
	public boolean hit(Column column) {
		// 先检测是否在柱子范围以内
		if (x > column.getX() - column.getWidth() / 2 - size / 2
				&& x < column.getX() + column.getWidth() / 2 + size / 2) {
			// 检测是否在柱子缝隙中
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
