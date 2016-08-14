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
	/**读取字节 逐个读取**/
	@Test
	public void testReadFileByBytes(){
		String path = this.getClass().getResource("").getPath();
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		FileDemo fd = new FileDemo();
		fd.readFileByBytes(path+name);
	}
	/**读取字节 有缓冲区**/
	@Test
	public void testReadFileByBytes2(){
		String path = this.getClass().getResource("").getPath();
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		FileDemo fd = new FileDemo();
		fd.readFileByBytes2(path+name);
	}
	/**读取字符 逐个读取**/
	@Test
	public void testReadFileByChars(){
		String path = this.getClass().getResource("").getPath();
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		FileDemo fd = new FileDemo();
		fd.readFileByChars(path+name);
	}
	/**按行都读取**/
	@Test
	public void testReadFileByLines(){
		String path = this.getClass().getResource("").getPath();
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		FileDemo fd = new FileDemo();
		fd.readFileByLines(path+name);
	}
	/**随机读取文件
	 *  读取一个很大的文件也是很快的
	 */
	@Test
	public void testReadFileByRandomAccess(){
		String path = this.getClass().getResource("").getPath();
		String name = "AppTest.txt";
		System.out.println("读取文件:"+path+name);
		FileDemo fd = new FileDemo();
		fd.readFileByRandomAccess("E:\\neu6\\Ip.Man.1.2008.720p.BluRay.x264.AAC.3Audio-iHD\\Ip.Man.1.2008.720p.BluRay.x264.AAC.3Audio-iHD.mp4");
//		fd.readFileByRandomAccess(path+name);
	}
}
