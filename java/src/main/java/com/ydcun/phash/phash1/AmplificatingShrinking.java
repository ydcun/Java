package com.ydcun.phash.phash1;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
/**
 * 图锟斤拷姆糯锟斤拷锟斤拷锟叫�
 * @author xiaoxu
 *
 */
public class AmplificatingShrinking {
	
	/**
	 * 双锟斤拷锟皆诧拷值锟斤拷图锟斤拷姆糯锟�
	 * @param srcPath
	 * @param distPath
	 * @param formatName
	 * @param k1
	 * @param k2
	 */
	public static void bilinearityInterpolation(String srcPath, String distPath,
			String formatName, float k1, float k2) {
		BufferedImage img = ImageDigital.readImg(srcPath);
		BufferedImage imgOut = bilinearityInterpolation(img, k1, k2);
		ImageDigital.writeImg(imgOut, formatName, distPath);
	}
	/**
	 * 双锟斤拷锟皆诧拷值锟斤拷图锟斤拷姆糯锟�
	 * 
	 * @param img
	 *            要锟斤拷小锟斤拷图锟斤拷锟斤拷锟�
	 * @param k1
	 *            要锟斤拷小锟斤拷锟叫憋拷锟斤拷
	 * @param k2
	 *            要锟斤拷小锟斤拷锟叫憋拷锟斤拷
	 * @return 锟斤拷锟截达拷锟斤拷锟斤拷图锟斤拷锟斤拷锟�
	 */
	public static BufferedImage bilinearityInterpolation(BufferedImage img, float k1, float k2) {
		if (k1 < 1 || k2 < 1) {// 锟斤拷锟絢1 <1 || k2<1锟斤拷锟斤拷图片锟斤拷小锟斤拷锟斤拷锟角放达拷
			System.err
					.println("this is shrink image funcation, please set k1<=1 and k2<=1锟斤拷");
			return null;
		}
		float ii = 1 / k1; // 锟斤拷锟斤拷锟斤拷锟叫硷拷锟�
		float jj = (1 / k2); // 锟斤拷锟斤拷锟斤拷锟叫硷拷锟�
		int dd = (int) (ii * jj);
		// int m=0 , n=0;
		int imgType = img.getType();
		int w = img.getWidth(); // 原图片锟侥匡拷
		int h = img.getHeight(); // 原图片锟侥匡拷
		int m = Math.round(k1 * w); // 锟脚达拷锟酵计拷目锟�
		int n = Math.round(k2 * h); // 锟脚达拷锟酵计拷目锟�
		int[] pix = new int[w * h];
		pix = img.getRGB(0, 0, w, h, pix, 0, w);
		/*
		 * System.out.println(w + " * " + h); System.out.println(m + " * " + n);
		 */
		int[] newpix = new int[m * n];

		for (int j = 0; j < h - 1; j++) {
			for (int i = 0; i < w - 1; i++) {
				int x0 = Math.round(i * k1);
				int y0 = Math.round(j * k2);
				int x1, y1;
				if (i == w - 2) {
					x1 = m - 1;
				} else {
					x1 = Math.round((i + 1) * k1);
				}
				if (j == h - 2) {
					y1 = n - 1;
				} else {
					y1 = Math.round((j + 1) * k2);
				}
				int d1 = x1 - x0;
				int d2 = y1 - y0;
				if (0 == newpix[y0 * m + x0]) {
					newpix[y0 * m + x0] = pix[j * w + i];
				}
				if (0 == newpix[y0 * m + x1]) {
					if (i == w - 2) {
						newpix[y0 * m + x1] = pix[j * w + w - 1];
					} else {
						newpix[y0 * m + x1] = pix[j * w + i + 1];
					}
				}
				if (0 == newpix[y1 * m + x0]) {
					if (j == h - 2) {
						newpix[y1 * m + x0] = pix[(h - 1) * w + i];
					} else {
						newpix[y1 * m + x0] = pix[(j + 1) * w + i];
					}
				}
				if (0 == newpix[y1 * m + x1]) {
					if (i == w - 2 && j == h - 2) {
						newpix[y1 * m + x1] = pix[(h - 1) * w + w - 1];
					} else {
						newpix[y1 * m + x1] = pix[(j + 1) * w + i + 1];
					}
				}
				int r, g, b;
				float c;
				ColorModel cm = ColorModel.getRGBdefault();
				for (int l = 0; l < d2; l++) {
					for (int k = 0; k < d1; k++) {
						if (0 == l) {
							// f(x,0) = f(0,0) + c1*(f(1,0)-f(0,0))
							if (j < h - 1 && newpix[y0 * m + x0 + k] == 0) {
								c = (float) k / d1;
								r = cm.getRed(newpix[y0 * m + x0])
										+ (int) (c * (cm.getRed(newpix[y0 * m
												+ x1]) - cm.getRed(newpix[y0
												* m + x0])));// newpix[(y0+l)*m
																// + k]
								g = cm.getGreen(newpix[y0 * m + x0])
										+ (int) (c * (cm.getGreen(newpix[y0 * m
												+ x1]) - cm.getGreen(newpix[y0
												* m + x0])));
								b = cm.getBlue(newpix[y0 * m + x0])
										+ (int) (c * (cm.getBlue(newpix[y0 * m
												+ x1]) - cm.getBlue(newpix[y0
												* m + x0])));
								newpix[y0 * m + x0 + k] = new Color(r, g, b)
										.getRGB();
							}
							if (j + 1 < h && newpix[y1 * m + x0 + k] == 0) {
								c = (float) k / d1;
								r = cm.getRed(newpix[y1 * m + x0])
										+ (int) (c * (cm.getRed(newpix[y1 * m
												+ x1]) - cm.getRed(newpix[y1
												* m + x0])));
								g = cm.getGreen(newpix[y1 * m + x0])
										+ (int) (c * (cm.getGreen(newpix[y1 * m
												+ x1]) - cm.getGreen(newpix[y1
												* m + x0])));
								b = cm.getBlue(newpix[y1 * m + x0])
										+ (int) (c * (cm.getBlue(newpix[y1 * m
												+ x1]) - cm.getBlue(newpix[y1
												* m + x0])));
								newpix[y1 * m + x0 + k] = new Color(r, g, b)
										.getRGB();
							}
							// System.out.println(c);
						} else {
							// f(x,y) = f(x,0) + c2*f(f(x,1)-f(x,0))
							c = (float) l / d2;
							r = cm.getRed(newpix[y0 * m + x0 + k])
									+ (int) (c * (cm.getRed(newpix[y1 * m + x0
											+ k]) - cm.getRed(newpix[y0 * m
											+ x0 + k])));
							g = cm.getGreen(newpix[y0 * m + x0 + k])
									+ (int) (c * (cm.getGreen(newpix[y1 * m
											+ x0 + k]) - cm.getGreen(newpix[y0
											* m + x0 + k])));
							b = cm.getBlue(newpix[y0 * m + x0 + k])
									+ (int) (c * (cm.getBlue(newpix[y1 * m + x0
											+ k]) - cm.getBlue(newpix[y0 * m
											+ x0 + k])));
							newpix[(y0 + l) * m + x0 + k] = new Color(r, g, b)
									.getRGB();
							// System.out.println((int)(c*(cm.getRed(newpix[y1*m
							// + x0+k]) - cm.getRed(newpix[y0*m + x0+k]))));
						}
					}
					if (i == w - 2 || l == d2 - 1) { // 锟斤拷锟揭伙拷械募锟斤拷锟�
						// f(1,y) = f(1,0) + c2*f(f(1,1)-f(1,0))
						c = (float) l / d2;
						r = cm.getRed(newpix[y0 * m + x1])
								+ (int) (c * (cm.getRed(newpix[y1 * m + x1]) - cm
										.getRed(newpix[y0 * m + x1])));
						g = cm.getGreen(newpix[y0 * m + x1])
								+ (int) (c * (cm.getGreen(newpix[y1 * m + x1]) - cm
										.getGreen(newpix[y0 * m + x1])));
						b = cm.getBlue(newpix[y0 * m + x1])
								+ (int) (c * (cm.getBlue(newpix[y1 * m + x1]) - cm
										.getBlue(newpix[y0 * m + x1])));
						newpix[(y0 + l) * m + x1] = new Color(r, g, b).getRGB();
					}
				}
			}
		}
		/*
		 * for(int j=0; j<50; j++){ for(int i=0; i<50; i++) {
		 * System.out.print(new Color(newpix[j*m + i]).getRed() + "\t"); }
		 * System.out.println(); }
		 */
		BufferedImage imgOut = new BufferedImage(m, n, imgType);

		imgOut.setRGB(0, 0, m, n, newpix, 0, m);
		return imgOut;
	}
	
	/**
	 * 双锟斤拷锟斤拷锟斤拷值
	 * 
	 * @param srcPath
	 *            原图锟斤拷锟铰凤拷锟�
	 * @param destPath
	 *            目锟斤拷图锟斤拷锟铰凤拷锟�
	 * @param formatName
	 *            图锟斤拷锟侥硷拷锟斤拷式
	 * @param k1
	 *            图锟斤拷目锟脚达拷锟斤拷锟�
	 * @param k2
	 *            图锟斤拷母叻糯锟斤拷锟斤拷
	 */
	/*public static void biCubicInterpolationScale(String srcPath,
			String destPath, String formatName, float k1, float k2) {
		BufferedImage img = ImageDigital.readImg(srcPath);
		int w = img.getWidth();
		int h = img.getHeight();
		int destW = Math.round(w * k1);
		int destH = Math.round(h * k2);
		int pix[] = new int[w * h];
		int type = img.getType();
		img.getRGB(0, 0, w, h, pix, 0, w);
		int[] newpix = BiCubicInterpolationScale.imgScale(pix, w, h, destW,
				destH);

		BufferedImage imgOut = new BufferedImage(destW, destH, type);
		imgOut.setRGB(0, 0, destW, destH, newpix, 0, destW);
		ImageDigital.writeImg(imgOut, formatName, destPath);
	}*/
	
	/**
	 * 锟饺硷拷锟斤拷锟斤拷锟斤拷锟酵硷拷锟脚达拷(锟斤拷小)
	 * 
	 * @param img
	 *            要锟脚达拷(锟斤拷小)锟斤拷图锟斤拷锟斤拷锟�
	 * @param k1
	 *            要锟脚达拷(锟斤拷小)锟斤拷锟叫憋拷锟斤拷
	 * @param k2
	 *            要锟脚达拷(锟斤拷小)锟斤拷锟叫憋拷锟斤拷
	 * @return 锟斤拷锟截达拷锟斤拷锟斤拷图锟斤拷锟斤拷锟�
	 */
	public static BufferedImage flex(BufferedImage img, float k1, float k2) {
		float ii = 1 / k1; // 锟斤拷锟斤拷锟斤拷锟叫硷拷锟�
		float jj = 1 / k2; // 锟斤拷锟斤拷锟斤拷锟叫硷拷锟�
		// int m=0 , n=0;
		int imgType = img.getType();
		int w = img.getWidth();
		int h = img.getHeight();
		int m = (int) (k1 * w);
		int n = (int) (k2 * h);
		int[] pix = new int[w * h];
		pix = img.getRGB(0, 0, w, h, pix, 0, w);
		System.out.println(w + " * " + h);
		System.out.println(m + " * " + n);
		int[] newpix = new int[m * n];

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				newpix[j * m + i] = pix[(int) (jj * j) * w + (int) (ii * i)];
			}
		}
		System.out.println((int) ((m - 1) * ii));
		System.out.println("m:" + m + " n:" + n);

		BufferedImage imgOut = new BufferedImage(m, n, imgType);

		imgOut.setRGB(0, 0, m, n, newpix, 0, m);
		return imgOut;
	}

	/**
	 * 锟饺硷拷锟斤拷锟斤拷锟斤拷锟酵硷拷锟脚达拷(锟斤拷小)
	 * 
	 * @param img
	 *            要锟脚达拷(锟斤拷小)锟斤拷图锟斤拷锟斤拷锟�
	 * @param m
	 *            锟脚达拷(锟斤拷小)锟斤拷图锟斤拷目锟�
	 * @param n
	 *            锟脚达拷(锟斤拷小)锟斤拷图锟斤拷母锟�
	 * @return 锟斤拷锟截达拷锟斤拷锟斤拷图锟斤拷锟斤拷锟�
	 */
	public static BufferedImage flex(BufferedImage img, int m, int n) {
		float k1 = (float) m / img.getWidth();
		float k2 = (float) n / img.getHeight();
		return flex(img, k1, k2);
	}
	/**
	 * 锟饺硷拷锟斤拷锟斤拷锟斤拷锟酵硷拷锟脚达拷(锟斤拷小)
	 * @param srcPath
	 * @param distPath
	 * @param formatName
	 * @param m
	 * @param n
	 */
	public static void filexIsometry(String srcPath, String distPath,
			String formatName, int m, int n) {
		BufferedImage img = ImageDigital.readImg(srcPath);
		BufferedImage imgOut = flex(img, m, n);
		ImageDigital.writeImg(imgOut, formatName, distPath);
	}
	
	/**
	 * 锟街诧拷锟斤拷值锟斤拷图锟斤拷锟斤拷小
	 * 
	 * @param img
	 *            要锟斤拷小锟斤拷图锟斤拷锟斤拷锟�
	 * @param k1
	 *            要锟斤拷小锟斤拷锟叫憋拷锟斤拷
	 * @param k2
	 *            要锟斤拷小锟斤拷锟叫憋拷锟斤拷
	 * @return 锟斤拷锟截达拷锟斤拷锟斤拷图锟斤拷锟斤拷锟�
	 */
	public static BufferedImage shrink(BufferedImage img, float k1, float k2) {
		if (k1 > 1 || k2 > 1) {// 锟斤拷锟絢1 >1 || k2>1锟斤拷锟斤拷图片锟脚大，诧拷锟斤拷锟斤拷小
			System.err
					.println("this is shrink image funcation, please set k1<=1 and k2<=1锟斤拷");
			return null;
		}
		float ii = 1 / k1; // 锟斤拷锟斤拷锟斤拷锟叫硷拷锟�
		float jj = 1 / k2; // 锟斤拷锟斤拷锟斤拷锟叫硷拷锟�
		int dd = (int) (ii * jj);
		// int m=0 , n=0;
		int imgType = img.getType();
		int w = img.getWidth();
		int h = img.getHeight();
		int m = Math.round(k1 * w);
		int n = Math.round(k2 * h);
		int[] pix = new int[w * h];
		pix = img.getRGB(0, 0, w, h, pix, 0, w);
		System.out.println(w + " * " + h);
		System.out.println(m + " * " + n);
		int[] newpix = shrink(pix, w, h, m, n);
	
		/*for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				int r = 0, g = 0, b = 0;
				ColorModel cm = ColorModel.getRGBdefault();
				for (int k = 0; k < (int) jj; k++) {
					for (int l = 0; l < (int) ii; l++) {
						r = r
								+ cm.getRed(pix[(int) (jj * j + k) * w
										+ (int) (ii * i + l)]);
						g = g
								+ cm.getGreen(pix[(int) (jj * j + k) * w
										+ (int) (ii * i + l)]);
						b = b
								+ cm.getBlue(pix[(int) (jj * j + k) * w
										+ (int) (ii * i + l)]);
					}
				}
				r = r / dd;
				g = g / dd;
				b = b / dd;
				newpix[j * m + i] = 255 << 24 | r << 16 | g << 8 | b;
				// 255<<24 | r<<16 | g<<8 | b 锟斤拷锟斤拷锟绞斤拷锟斤拷锟揭伙拷拢锟斤拷锟缴拷锟絉GB锟斤拷锟节达拷锟斤拷锟斤拷
				// 锟皆讹拷锟斤拷锟狡碉拷锟斤拷式锟斤拷锟斤拷模锟斤拷锟斤拷业锟斤拷锟�1-8位锟斤拷示blue锟斤拷9-16锟斤拷示green锟斤拷17-24锟斤拷示red
				// 锟斤拷锟斤拷"<<24" "<<16" "<<8"锟街憋拷锟绞撅拷锟斤拷锟�24,16,8位
	
				// newpix[j*m + i] = new Color(r,g,b).getRGB();
			}
		}*/
	
		BufferedImage imgOut = new BufferedImage(m, n, imgType);
	
		imgOut.setRGB(0, 0, m, n, newpix, 0, m);
		return imgOut;
	}
	/**
	 * 锟街诧拷锟斤拷值锟斤拷图锟斤拷锟斤拷小
	 * @param pix 图锟斤拷锟斤拷锟斤拷鼐锟斤拷锟�
	 * @param w 原图锟斤拷目锟�
	 * @param h 原图锟斤拷母锟�
	 * @param m 锟斤拷小锟斤拷图锟斤拷目锟�
	 * @param n 锟斤拷小锟斤拷图锟斤拷母锟�
	 * @return
	 */
	public static int[] shrink(int[] pix, int w, int h, int m, int n) {
		float k1 = (float) m / w;
		float k2 = (float) n / h;
		int ii = (int)(1 / k1); // 锟斤拷锟斤拷锟斤拷锟叫硷拷锟�
		int jj = (int)(1 / k2); // 锟斤拷锟斤拷锟斤拷锟叫硷拷锟�
		int dd = ii * jj;
		// int m=0 , n=0;
		// int imgType = img.getType();
		int[] newpix = new int[m * n];

		for (int j = 0; j < n; j++) {
			for (int i = 0; i < m; i++) {
				int r = 0, g = 0, b = 0;
				ColorModel cm = ColorModel.getRGBdefault();
				for (int k = 0; k <  jj; k++) {
					for (int l = 0; l <  ii; l++) {
						r = r
								+ cm.getRed(pix[(jj * j + k) * w
										+  (ii * i + l)]);
						g = g
								+ cm.getGreen(pix[(jj * j + k) * w
										+  (ii * i + l)]);
						b = b
								+ cm.getBlue(pix[ (jj * j + k) * w
										+  (ii * i + l)]);
					}
				}
				r = r / dd;
				g = g / dd;
				b = b / dd;
				newpix[j * m + i] = 255 << 24 | r << 16 | g << 8 | b;
				// 255<<24 | r<<16 | g<<8 | b 锟斤拷锟斤拷锟绞斤拷锟斤拷锟揭伙拷拢锟斤拷锟缴拷锟絉GB锟斤拷锟节达拷锟斤拷锟斤拷
				// 锟皆讹拷锟斤拷锟狡碉拷锟斤拷式锟斤拷锟斤拷模锟斤拷锟斤拷业锟斤拷锟�1-8位锟斤拷示blue锟斤拷9-16锟斤拷示green锟斤拷17-24锟斤拷示red
				// 锟斤拷锟斤拷"<<24" "<<16" "<<8"锟街憋拷锟绞撅拷锟斤拷锟�24,16,8位

				// newpix[j*m + i] = new Color(r,g,b).getRGB();
			}
		}
		return newpix;
	}

	/**
	 * 锟街诧拷锟斤拷值锟斤拷图锟斤拷锟斤拷小
	 * 
	 * @param img
	 *            要锟斤拷小锟斤拷图锟斤拷锟斤拷锟�
	 * @param m
	 *            锟斤拷小锟斤拷图锟斤拷目锟�
	 * @param n
	 *            锟斤拷小锟斤拷图锟斤拷母锟�
	 * @return 锟斤拷锟截达拷锟斤拷锟斤拷图锟斤拷锟斤拷锟�
	 */
	public static BufferedImage shrink(BufferedImage img, int m, int n) {
		float k1 = (float) m / img.getWidth();
		float k2 = (float) n / img.getHeight();
		return shrink(img, k1, k2);
	}
	
}
