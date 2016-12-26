package com.ydcun.phash.phash0;
import java.awt.Graphics2D;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.imageio.ImageIO;
/*
* pHash-like image hash. 
* Author: Elliot Shepherd (elliot@jarofworms.com
* Based On: http://www.hackerfactor.com/blog/index.php?/archives/432-Looks-Like-It.html
*/
public class Phash {

   private int size = 32;
   private int smallerSize = 8;
   
   public Phash() {
       initCoefficients();
   }
   
   public Phash(int size, int smallerSize) {
       this.size = size;
       this.smallerSize = smallerSize;
       
       initCoefficients();
   }
   
   public int distance(String s1, String s2) {
       int counter = 0;
       for (int k = 0; k < s1.length();k++) {
           if(s1.charAt(k) != s2.charAt(k)) {
               counter++;
           }
       }
       return counter;
   }
   
   // Returns a 'binary string' (like. 001010111011100010) which is easy to do a hamming distance on. 
   public String getHash(InputStream is) throws Exception {
       BufferedImage img = ImageIO.read(is);
       
       /* 1. Reduce size. 
        * Like Average Hash, pHash starts with a small image. 
        * However, the image is larger than 8x8; 32x32 is a good size. 
        * This is really done to simplify the DCT computation and not 
        * because it is needed to reduce the high frequencies.
        */
       img = resize(img, size, size);
       
       /* 2. Reduce color. 
        * The image is reduced to a grayscale just to further simplify 
        * the number of computations.
        */
       img = grayscale(img);
       
       double[][] vals = new double[size][size];
       
       for (int x = 0; x < img.getWidth(); x++) {
           for (int y = 0; y < img.getHeight(); y++) {
               vals[x][y] = getBlue(img, x, y);
           }
       }
       
       /* 3. Compute the DCT. 
        * The DCT separates the image into a collection of frequencies 
        * and scalars. While JPEG uses an 8x8 DCT, this algorithm uses 
        * a 32x32 DCT.
        */
       long start = System.currentTimeMillis();
       double[][] dctVals = applyDCT(vals);
       //System.out.println("DCT: " + (System.currentTimeMillis() - start));
       
       /* 4. Reduce the DCT. 
        * This is the magic step. While the DCT is 32x32, just keep the 
        * top-left 8x8. Those represent the lowest frequencies in the 
        * picture.
        */
       /* 5. Compute the average value. 
        * Like the Average Hash, compute the mean DCT value (using only 
        * the 8x8 DCT low-frequency values and excluding the first term 
        * since the DC coefficient can be significantly different from 
        * the other values and will throw off the average).
        */
       double total = 0;
       
       for (int x = 0; x < smallerSize; x++) {
           for (int y = 0; y < smallerSize; y++) {
               total += dctVals[x][y];
           }
       }
       total -= dctVals[0][0];
       
       double avg = total / (double) ((smallerSize * smallerSize) - 1);
   
       /* 6. Further reduce the DCT. 
        * This is the magic step. Set the 64 hash bits to 0 or 1 
        * depending on whether each of the 64 DCT values is above or 
        * below the average value. The result doesn't tell us the 
        * actual low frequencies; it just tells us the very-rough 
        * relative scale of the frequencies to the mean. The result 
        * will not vary as long as the overall structure of the image 
        * remains the same; this can survive gamma and color histogram 
        * adjustments without a problem.
        */
       String hash = "";
       
       for (int x = 0; x < smallerSize; x++) {
           for (int y = 0; y < smallerSize; y++) {
               if (x != 0 && y != 0) {
                   hash += (dctVals[x][y] > avg?"1":"0");
               }
           }
       }
       
       return hash;
   }
   
   private BufferedImage resize(BufferedImage image, int width,    int height) {
       BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
       Graphics2D g = resizedImage.createGraphics();
       g.drawImage(image, 0, 0, width, height, null);
       g.dispose();
       return resizedImage;
   }
   
   private ColorConvertOp colorConvert = new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY), null);

   private BufferedImage grayscale(BufferedImage img) {
       colorConvert.filter(img, img);
       return img;
   }
   
   private static int getBlue(BufferedImage img, int x, int y) {
       return (img.getRGB(x, y)) & 0xff;
   }
   
   // DCT function stolen from http://stackoverflow.com/questions/4240490/problems-with-dct-and-idct-algorithm-in-java

   private double[] c;
   private void initCoefficients() {
       c = new double[size];
       
       for (int i=1;i<size;i++) {
           c[i]=1;
       }
       c[0]=1/Math.sqrt(2.0);
   }
   
   private double[][] applyDCT(double[][] f) {
       int N = size;
       
       double[][] F = new double[N][N];
       for (int u=0;u<N;u++) {
         for (int v=0;v<N;v++) {
           double sum = 0.0;
           for (int i=0;i<N;i++) {
             for (int j=0;j<N;j++) {
               sum+=Math.cos(((2*i+1)/(2.0*N))*u*Math.PI)*Math.cos(((2*j+1)/(2.0*N))*v*Math.PI)*(f[i][j]);
             }
           }
           sum*=((c[u]*c[v])/4.0);
           F[u][v] = sum;
         }
       }
       return F;
   }
   public Map<String,Integer> discern(File file,File dir) throws Exception{
	   //Integer VPT = 20;
	   if(!dir.exists()){
    	   System.out.println("dir not find");
    	   return null;
       }
	   Phash p = new Phash();
       File[] files = dir.listFiles();
       String hash1=null;
       String hash2=null;
       //设备编号
       Map<String,Integer> r = new TreeMap<String,Integer>(){
    	   
       };//记录小于阈值的设备
	   hash1 = p.getHash(new FileInputStream(file));
	   String key = null;
	   Integer value =null;
	   Integer tempValue = 0;
       for(File f:files){
    	   if(f.getName().equals(file.getName())){
    		   continue;
    	   }
		   hash2 = p.getHash(new FileInputStream(f));
		   key = f.getName().substring(0, f.getName().indexOf("_"));
		   tempValue = p.distance(hash1, hash2);
		  // if(tempValue<=VPT){
			   value = r.get(key);
			   if(value==null){
				   value = tempValue;
			   }else{
				   value = value>tempValue?tempValue:value;
			   }
			   r.put(key, value);
		  // }
       }
       return r;
   }
   public static void main(String[] args) {
       
       Phash p = new Phash();
       String image1;
       String image2;
       try {
           File dir = new File("d:/image");
           if(!dir.exists()){
        	   System.out.println("dir not find");
        	   return;
           }
           File[] files = dir.listFiles();
           Map<String, Integer> r =null;
           for(File f:files){
        	   System.out.println(f.getAbsolutePath());
        	   r = p.discern(f, dir);
        	   System.out.print(f.getName()+":");
        	 //这里将map.entrySet()转换成list
               List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(r.entrySet());
               //然后通过比较器来实现排序
               Collections.sort(list,new Comparator<Map.Entry<String,Integer>>() {
                   //升序排序
                   public int compare(Entry<String, Integer> o1,
                           Entry<String, Integer> o2) {
                       return o1.getValue().compareTo(o2.getValue());
                   }
               });
               for(Map.Entry<String,Integer> mapping:list){ 
                      System.out.print (mapping.getKey()+":"+mapping.getValue()+","); 
               } 
    		   System.out.println();
           }
           
           
       } catch (FileNotFoundException e) {
           e.printStackTrace();
       } catch (Exception e) {
           e.printStackTrace();
       }

   }
} 