/**
 * 
 */
package com.ydcun.yzm;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

/**
 * @author ydcun-psjs
 *
 */
public class Pretreatment {
	public static void binaryImage(String path,String name) throws IOException {
		File file = new File(path+name);
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_BINARY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
			}
		}
		grayImage = rotateImage(grayImage, 90);
		grayImage = noiseLine(grayImage);
		File newFile = new File(path + "binaryImage/" + name);
		ImageIO.write(grayImage, "jpg", newFile);
	}

	public static void grayImage(String path,String name) throws IOException {
		File file = new File(path+name);
		BufferedImage image = ImageIO.read(file);

		int width = image.getWidth();
		int height = image.getHeight();

		BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);// 重点，技巧在这个参数BufferedImage.TYPE_BYTE_GRAY
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				int rgb = image.getRGB(i, j);
				grayImage.setRGB(i, j, rgb);
			}
		}
		grayImage = rotateImage(grayImage, 90);
		File newFile = new File(path+"grayImage/"+name);
		ImageIO.write(grayImage, "jpg", newFile);
	}
	
	public static BufferedImage noiseLine(final BufferedImage bufferedimage){
		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img = new BufferedImage(w, h, type);
		for(int i=0;i<h;i++){
			for(int j=0;j<w;j++){
				img.setRGB(j, i, bufferedimage.getRGB(j, i)<-2?-16777216:-1);
			}
		}
		for(int i=0;i<h;i++){
			for(int j=0;j<w-5;j++){
				if(img.getRGB(j, i)<-2){
					img.setRGB(j, i, img.getRGB(j+2, i));
				}
			}
		}
		return img;
	}
	/**
	 * 图片旋转
	 * @param bufferedimage
	 * @param degree
	 * @return
	 */
	public static BufferedImage rotateImage(final BufferedImage bufferedimage, final int degree) {

		int w = bufferedimage.getWidth();
		int h = bufferedimage.getHeight();
		int type = bufferedimage.getColorModel().getTransparency();
		BufferedImage img;
		if (90 == degree || 270 == degree) {
		img = new BufferedImage(h, w, type);
		} else {
		img = new BufferedImage(w, h, type);
		}
		Graphics2D graphics2d;
		graphics2d = img.createGraphics();
		graphics2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
		RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		if (270 == degree) {
		graphics2d.rotate(Math.toRadians(degree), w / 2, w / 2);
		} else if (90 == degree) {
		graphics2d.rotate(Math.toRadians(degree), h / 2, h / 2);
		}
		graphics2d.drawImage(bufferedimage, 0, 0, null);
		graphics2d.dispose();
		return img;
		}
	public static void main(String[] args) throws Exception {
		String pathImage = Download.class.getResource(".").toString().replaceAll("target/classes", "src/main/java")
				.substring(6) + "image/";
		for(int i=0;i<100;i++){
			String imageName = i+".jpg";
			Pretreatment.binaryImage(pathImage,imageName);
//			Pretreatment.grayImage(pathImage, imageName);
//			System.out.println(pathImage);
			
		}
	}
}
