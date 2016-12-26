package com.ydcun.phash.phash1;

import java.awt.image.BufferedImage;
import java.math.BigInteger;


public class Fingerprint {
	/**
	 * ͼƬ��С��Ŀ�?
	 */
	public static final int FWIDTH = 8;
	/**
	 *  ͼƬ��С��ĸ�?
	 */
	public static final int FHEIGHT = 8;
	/**
	 * ���ͼƬ��ָ����?
	 * @param srcPath ͼƬ���ڵ�·��
	 * @return ͼƬ��ָ����
	 */
	public static String getFingerprint(String srcPath) {
		BufferedImage img = ImageDigital.readImg(srcPath);
		int w = img.getWidth();
		int h = img.getHeight();
		int pix[] = new int[w * h];
		pix = img.getRGB(0, 0, w, h, pix, 0, w);
		long l = getFingerprint(pix, w, h);
		StringBuilder sb = new StringBuilder(Long.toHexString(l));
		if(sb.length() < 16) {
			int n = 16-sb.length();
			for(int i=0; i<n; i++) {
				sb.insert(0, "0");
			}
		}
		//System.out.println();
		return sb.toString();
	}
	/**
	 *  ��ͼƬ��ָ��
	 * @param pix ͼ������ؾ���?
	 * @param w ͼ��Ŀ�?
	 * @param h ͼ��ĸ�?
	 * @return
	 */
	public static long getFingerprint(int[] pix, int w, int h) {
		pix = AmplificatingShrinking.shrink(pix, w, h, FWIDTH, FHEIGHT);
		int[] newpix = ImageDigital.grayImage(pix, FWIDTH, FHEIGHT);
		int avrPix = averageGray(newpix, FWIDTH, FHEIGHT);
		//int hist[] = new int[FWIDTH*FHEIGHT];	
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<FWIDTH*FHEIGHT; i++) {
			if(newpix[i] >= avrPix) {
				sb.append("1");	
				//hist[i] = 1;
			} else {
				sb.append("0");	
				//hist[i] = 0;
			}
			//sb.append(hist[i]);			
		}
		//System.out.println(sb.toString());
		long result = 0;
		if(sb.charAt(0) == '0') {
			result = Long.parseLong(sb.toString(), 2);
		} else {
			//�����һ���ַ���?1�����ʾ����������ֱ��ת����long��
			result = 0x8000000000000000l ^ Long.parseLong(sb.substring(1), 2);
		}
		
		return result;
	}
	/**
	 * ��Ҷ�ͼ��ľ�ֵ
	 * @param pix ͼ������ؾ���?
	 * @param w ͼ��Ŀ�?
	 * @param h ͼ��ĸ�?
	 * @return �ҶȾ�ֵ
	 */
	private static int averageGray(int[] pix, int w, int h) {
		int sum = 0;
		for(int i=0; i<h; i++) {
			for(int j=0; j<w; j++) {
				sum = sum+pix[i*w + j];
			}
			
		}
		return (int)(sum/(w*h));
	}
	/**
	 * ���㺺������
	 * @param s1 ָ����1
	 * @param s2 ָ����2
	 * @return ��������
	 */
	public static int hammingDistance(String s1, String s2) {
		int count = 0;
		for(int i=0; i<s1.length(); i++) {
			if(s1.charAt(i) != s2.charAt(i)) {
				count ++;
			}
		}
		return count;
	}
	/**
	 * ����pHash�㷨��ָ����
	  * @param srcPath ͼƬ���ڵ�·��
	 * @return ͼƬ��ָ����
	 */
	public static String getFingerprintPhash(String srcPath) {
		BufferedImage img = ImageDigital.readImg(srcPath);
		int w = img.getWidth();
		int h = img.getHeight();
		int pix[] = new int[w * h];
		pix = img.getRGB(0, 0, w, h, pix, 0, w);
		
		pix = AmplificatingShrinking.shrink(pix, w, h, 32, 32);
		pix = ImageDigital.grayImage(pix, 32, 32);
		Transformation tf = new Transformation();
		int[] dctPix = tf.DCT(pix, 32);
		int avrPix = averageGray(dctPix, FWIDTH, FHEIGHT);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<FHEIGHT; i++) {			
			for(int j=0; j<FWIDTH; j++) {		
				if(dctPix[i*FWIDTH + j] >= avrPix) {
					sb.append("1");	
				} else {
					sb.append("0");	
				}
			}
		}
		//System.out.println(sb.toString());
		long result = 0;
		if(sb.charAt(0) == '0') {
			result = Long.parseLong(sb.toString(), 2);
		} else {
			//�����һ���ַ���?1�����ʾ����������ֱ��ת����long��
			result = 0x8000000000000000l ^ Long.parseLong(sb.substring(1), 2);
		}
		
		sb = new StringBuilder(Long.toHexString(result));
		if(sb.length() < 16) {
			int n = 16-sb.length();
			for(int i=0; i<n; i++) {
				sb.insert(0, "0");
			}
		}
		return sb.toString();
	}
	
	
	
	/*public static void averageHash() {
		
	}
	
	public static void pHash() {
		
	}*/
	
	/**
	 * @param args
	 * http://blog.csdn.net/luoweifu/article/details/8220992
	 */
	public static void main(String[] args) {
		String s1 = null, s2 = null;
		String path = Fingerprint.class.getResource(".").getPath();
		System.out.println(path.substring(1));
		s1 =  getFingerprintPhash(path+"/image/person.jpg");
		//s1 =  getFingerprint("F:\\image processing\\����ͼƬ�ز�\\images(0).jpg");
		System.out.println("source: " + s1);
		
		for(int i=1; i<=11; i++) {
			s2 =  getFingerprintPhash(path+"/image/person"+ i + ".jpg");
			//s2 =  getFingerprint("F:\\image processing\\����ͼƬ�ز�\\images ("+ i + ").jpg");
			//System.out.println("example:" +s2);
			System.out.print(i + "-" + hammingDistance(s1, s2) + "\t");
		}		
	}

}

/**
 * source:00c9cfdfdfdfff00
15	11	0	2	0	0

6	4	0	1	1	1

6	4	0	1	1	1

hash: 1-1	2-3	3-3	4-5	5-2	6-6	7-2	8-7	9-7	10-4	11-5	12-5	13-9	14-1	
phash:1-9	2-8	3-14	4-8	5-12	6-11	7-14	8-13	9-14	10-13	11-14	12-15	13-13	14-9
 */
