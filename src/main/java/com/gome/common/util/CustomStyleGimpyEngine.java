package com.gome.common.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import com.google.code.kaptcha.GimpyEngine;

/**
 * 自定义验证码样式
 * @author wuzhe
 *
 */
public class CustomStyleGimpyEngine implements GimpyEngine {

	private int imgH;
	private int imgW;

	@Override
	public BufferedImage getDistortedImage(BufferedImage image) {
		Graphics2D graph = (Graphics2D) image.getGraphics();
		imgH = image.getHeight();
		imgW = image.getWidth();
		// want lines put them in a variable so we migh configure these later
		int hstripes = imgH / 7;// 4;
		int vstripes = imgW / 7;// 8;
		// claculate space between lines
		int hspace = imgH / (hstripes + 1);
		int vspace = imgW / (vstripes + 1);
		// draw the horizontal stripes
		for (int i = hspace; i < imgH; i = i + hspace) {
			graph.setColor(Color.black);
			// graph.drawLine(0, i, imgW, i);
		}
		// draw the vertical stripes
		for (int i = vspace; i < imgW; i = i + vspace) {
			graph.setColor(Color.black);
			// graph.drawLine(i, 0, i, imgH);
		}
		// create a pixel array of the original image.
		// we need this later to do the operations on..
		int pix[] = new int[imgH * imgW];
		int j = 0;
		for (int j1 = 0; j1 < imgW; j1++) {
			for (int k1 = 0; k1 < imgH; k1++) {
				pix[j] = image.getRGB(j1, k1);
				j++;
			}
		}
		double distance = ranInt(imgW / 4, imgW / 3);
		// put the distortion in the (dead) middle
		int wMid = image.getWidth() / 2;
		int hMid = image.getHeight() / 2;
		// again iterate over all pixels..
		for (int x = 0; x < image.getWidth(); x++) {
			for (int y = 0; y < image.getHeight(); y++) {
				int relX = x - wMid;
				int relY = y - hMid;
				double d1 = Math.sqrt(relX * relX + relY * relY);
				if (d1 < distance) {

					int j2 = wMid
							+ (int) ((((d1 / distance) * distance) / d1) * (double) (x - wMid));
					int k2 = hMid
							+ (int) ((((d1 / distance) * distance) / d1) * (double) (y - hMid));
					image.setRGB(x, y, pix[j2 * imgH + k2]);
				}
			}
		}
		return image;
	}
	private int ranInt(int i, int j) {
		double d = Math.random();
		return (int) ((double) i + (double) ((j - i) + 1) * d);
	}

}

