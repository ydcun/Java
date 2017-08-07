package com.ydcun.yzm;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class NoiseLine {
	private final int M = 200;
	private final int N = 60;
	int a[][] = new int[this.M][this.N];
	int[] b = new int[this.M];
	int threshold = 18;
	int offset = 35;

	public NoiseLine(String path,String name) {
		int x = 0;
		int y = 0;
		try {
			final BufferedImage img = ImageIO.read(new File(path+name));
			this.renderImg = img;
			final int width = img.getWidth();
			final int height = img.getHeight();
			System.out.println(width + ":" + height);
			for (; x < height; ++x) {
				for (y = 0; y < width; ++y) {
					this.a[x][y] = CommonUtil.isBlack(img.getRGB(y, x), 700);
					//System.out.print(this.a[x][y] + " ");
				}
				//System.out.println(" ");
			}
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	private BufferedImage renderImg;

	public void genLine(int n) {
		if (n < this.offset) {
			this.b[n] = -1;
			this.genLine(n + 1);
		}
		if (n == this.M) {
			for (int i = 0; i < this.M; i++) {
				System.out.print(this.b[i] + " ");

			}
			System.out.println("");
		}
		if (n == this.offset) {
			for (int j = 0; j < this.N; j++) {
				if (this.a[this.offset][j] == 1) {
					this.b[this.offset] = j;
					this.genLine(n + 1);
				}
			}
		}
		if (n > 0 && n < this.M) {
			int hasMore = 0;
			if (this.b[n - 1] > 0 && this.b[n - 1] < this.N && this.a[n][this.b[n - 1]] == 1) {
				this.b[n] = this.b[n - 1];
				hasMore = 1;
				this.genLine(n + 1);
			} else {
				if (this.b[n - 1] > 0 && this.a[n][this.b[n - 1] - 1] == 1) {
					this.b[n] = this.b[n - 1] - 1;
					hasMore = 1;
					this.genLine(n + 1);
				}
				if (this.b[n - 1] < this.N - 1 && this.a[n][this.b[n - 1] + 1] == 1) {
					this.b[n] = this.b[n - 1] + 1;
					hasMore = 1;
					this.genLine(n + 1);
				}
			}
			if (n - this.offset > this.threshold && hasMore == 0) {
				for (int i = 0; i < n; i++) {
					if (this.b[i] > 0) {
//						this.renderImg.setRGB(this.b[i], i, Color.RED.getRGB());
						this.renderImg.setRGB(this.b[i], i, Color.WHITE.getRGB());
					}
				}
			}
		}

	}

	public void saveImg(String path,String name) {
		try {
			this.renderImg=Pretreatment.rotateImage(this.renderImg, 270);
			ImageIO.write(this.renderImg, "JPG", new File(path+"noiseLine/"+name));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String pathImage = Download.class.getResource(".").toString().replaceAll("target/classes", "src/main/java")
				.substring(6) + "image/binaryImage/";
		for(int i=0;i<1000;i++){
			String imageName = i+".jpg";
			final NoiseLine line = new NoiseLine(pathImage,imageName);
			line.genLine(0);
			line.saveImg(pathImage,imageName);
		}
	}

}