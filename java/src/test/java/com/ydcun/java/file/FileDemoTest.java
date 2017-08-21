/**
 * 
 */
package com.ydcun.java.file;

import org.junit.Test;

/**
 * @author ydcun-psjs
 * AppTest2.txt是GBK编码文件
 * AppTest.txt是UTF-8编码文件
 */
public class FileDemoTest {
	String path = this.getClass().getResource("").getPath();
	ReadFileDemo rfd = new ReadFileDemo();
	WriteFileDemo wfd = new WriteFileDemo();
	/**读取字节 逐个读取**/
	@Test
	public void testReadFileByBytes(){
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		rfd.readFileByBytes(path+name);
	}
	/**读取字节 有缓冲区**/
	@Test
	public void testReadFileByBytes2(){
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		rfd.readFileByBytes2(path+name);
	}
	/**读取字符 逐个读取**/
	@Test
	public void testReadFileByChars(){
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		rfd.readFileByChars(path+name);
	}
	/**按行都读取**/
	@Test
	public void testReadFileByLines(){
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		rfd.readFileByLines(path+name);
	}
	/**随机读取文件
	 *  读取一个很大的文件也是很快的
	 */
	@Test
	public void testReadFileByRandomAccess(){
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		ReadFileDemo fd = new ReadFileDemo();
		fd.readFileByRandomAccess("E:\\neu6\\Ip.Man.1.2008.720p.BluRay.x264.AAC.3Audio-iHD\\Ip.Man.1.2008.720p.BluRay.x264.AAC.3Audio-iHD.mp4");
//		fd.readFileByRandomAccess(path+name);
	}
	/**读取参数文件**/
	@Test
	public void testReadPropertiesFile(){
		String name = "config.properties";
		System.out.println("读取文件:"+path+name);
		rfd.readPropertiesFile(path+name);
	}
	/**FileOutputStream 写入文件**/
	@Test
	public void testWriteFile1(){
		String name = "c:/add.txt";
		wfd.byte2File1(name,"a测试".getBytes());
	}
	/**BufferedOutputStream 写入文件**/
	@Test
	public void testWriteFile2(){
		String name = "c:/add.txt";
		wfd.byte2File2(name,"a测试".getBytes());
	}
	/**字符串 写入文件**/
	@Test
	public void testWriteFile3(){
		String name = "c:/add.txt";
		wfd.string2File(name,"a测试");
	}
}
