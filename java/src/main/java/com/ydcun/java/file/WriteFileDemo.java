package com.ydcun.java.file;

import java.io.File;

import java.io.FileOutputStream;

import java.io.*;

public class WriteFileDemo {
	int count = 1000000;// 写文件行数
	/**
	 * FileOutputStream 写文件
	 */
	public void byte2File1(String fileName,byte[] array){
		FileOutputStream out = null;
		FileWriter fw = null;
		try {
			out = new FileOutputStream(new File(fileName));
			long begin = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				out.write(array);
			}
			out.close();
			long end = System.currentTimeMillis();
			System.out.println("FileOutputStream执行耗时:" + (end - begin) + " 豪秒");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**BufferedOutputStream 写入文件**/
	public void byte2File2(String fileName,byte[] array){
		FileOutputStream outSTr = null;
		BufferedOutputStream Buff = null;
		try {
			outSTr = new FileOutputStream(new File(fileName));
			Buff = new BufferedOutputStream(outSTr);
			long begin0 = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				Buff.write(array);
			}
			Buff.flush();
			Buff.close();
			long end0 = System.currentTimeMillis();
			System.out.println("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 豪秒");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				Buff.close();
				outSTr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	/**将字符串写入到文件**/
	public void string2File(String fileName,String str) {
		FileWriter fw = null;
		try {
			fw = new FileWriter(fileName);
			long begin3 = System.currentTimeMillis();
			for (int i = 0; i < count; i++) {
				fw.write(str+"\n\r");
			}
			fw.close();
			long end3 = System.currentTimeMillis();
			System.out.println("FileWriter执行耗时:" + (end3 - begin3) + " 豪秒");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}