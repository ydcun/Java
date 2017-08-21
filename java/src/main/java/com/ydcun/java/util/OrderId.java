/**
 * 
 */
package com.ydcun.java.util;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author ydcun-psjs
 *
 */
public class OrderId {
	public static final String dt                  = "yyyyMMddHHmmssSSS";
	// 最新前缀
	static String currPrefix = "";
	// 最新前缀，生成过的后缀列表
	static String oldSuffix = "";
	static DateFormat df = new SimpleDateFormat(dt);
	static DecimalFormat decF = new DecimalFormat("00");
	static Random rad = new Random();

	/**
	 * 返回一个唯一后缀
	 * 
	 * @param prefix
	 * @return
	 */
	public synchronized static String getSuffix() {
		String prefix = "I" + df.format(new Date());// 前缀
		String suffix = decF.format(rad.nextInt(100));// 后缀
		if(prefix.equals(currPrefix)){//同一毫秒内
			if(oldSuffix.indexOf(suffix)>-1){//已经分配过
				try {
					Thread.sleep(1);//延时一毫秒
				} catch (Exception e) {
					e.printStackTrace();
				}
				prefix = "I" + df.format(new Date());// 前缀
				currPrefix = prefix;//更改当前毫秒值
				suffix = decF.format(rad.nextInt(100));// 后缀
				oldSuffix="";//清空历史 后缀列表
				oldSuffix += ","+suffix;
				return prefix+suffix;
			}else{//生成的可用
				oldSuffix += ","+suffix;//添加到后缀列表中
				return prefix+suffix;
			}
		}else{
			currPrefix = prefix;//更改当前毫秒值
			return prefix+suffix;
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			String suffix = getSuffix();
			System.out.println(suffix);
		}
	}
}
