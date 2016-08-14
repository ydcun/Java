/**
 * 
 */
package com.ydcun.java.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.Arrays;

/**
 * @author ydcun-psjs
 *  字节读取二进制文件，图片，录音，视频
 *  字符读取文本文件
 *  
 *  read()方法每次只能读取一个字节，
 *  所以也只能读取由ASCII码范围内的一些字符。
 *  这些字符主要用于显示现代英语和其他西欧语言。
 *  而对于汉字等unicode中的字符则不能正常读取。只能以乱码的形式显示。
 *  
 *  对于read()方法的上述缺点，在read(byte[] b)中则得到了解决，就拿汉字来举例，
 *  一个汉字占有两个字节，则可以把参数数组b定义为大小为2的数组即可正常读取汉字了。
 *  当然b也可以定义为更大，比如如果b=new byte[4]的话，则每次可以读取两个汉字字符了，
 *  但是需要注意的是，如果此处定义b 的大小为3或7等奇数，
 *  则对于全是汉字的一篇文档则不能全部正常读写了。
 */
public class FileDemo {
	/**
	 * 逐个字节读取  
	 */
	public void readFileByBytes(String fileName){
		File file = new File(fileName);
		InputStream in = null;
		try{
			System.out.println("一次读取一个字节");
			in = new FileInputStream(file);
			int temp;
			int count=0;
			while((temp = in.read())!=-1){
				System.out.println(temp);
				count++;
			}
			System.out.println("共"+count+"字节");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	/**
	 * 读多个字节
	 */
	public void readFileByBytes2(String fileName){
		File file =new File(fileName);
		InputStream in = null;
		try{
			byte[] tempBytes=new byte[1024];
			int temp=0;
			in = new FileInputStream(file);
			while((temp=in.read(tempBytes))!=-1){
				System.out.println(Arrays.toString(tempBytes));
				System.out.write(tempBytes,0,temp);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(in!=null){
				try{
					in.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	/**字符方式逐个读取**/
	public void readFileByChars(String fileName){
		File file = new File(fileName);
		Reader reader =null;
		try{
			System.out.println("以字符方式逐个读取");
			reader = new InputStreamReader(new FileInputStream(file));
			int temp=0;
			while((temp = reader.read())!=-1){
				//window 下\n\r是换行，分开显示的时候会换行两侧，所以可以屏蔽一个
				if((char)temp!='\r'){
					System.out.println((char)temp);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**按行读取文件**/
	public void readFileByLines(String fileName){
		File file = new File(fileName);
		BufferedReader reader = null;
		try{
			System.out.print("以行的方式读取文件内容");
			reader = new BufferedReader(new FileReader(file));
			String tempString;
			int line=0;
			while((tempString = reader.readLine())!=null){
				System.out.println(""+(++line)+":"+tempString);
			}
		}catch(Exception e ){
			e.printStackTrace();
		}finally{
			if(reader!=null){
				try{
					reader.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	/**随机读取文件内容**/
    public static void readFileByRandomAccess(String fileName) {
        RandomAccessFile randomFile = null;
        try {
            System.out.println("随机读取一段文件内容：");
            // 打开一个随机访问文件流，按只读方式
            randomFile = new RandomAccessFile(fileName, "r");
            // 文件长度，字节数
            long fileLength = randomFile.length();
            System.out.println(fileLength);
            // 读文件的起始位置
            long beginIndex = (fileLength > 2293365500L) ? 2293365500L : 0;
            // 将读文件的开始位置移到beginIndex位置。
            randomFile.seek(beginIndex);
            byte[] bytes = new byte[10];
            int byteread = 0;
            // 一次读10个字节，如果文件内容不足10个字节，则读剩下的字节。
            // 将一次读取的字节数赋给byteread
            while ((byteread = randomFile.read(bytes)) != -1) {
                System.out.write(bytes, 0, byteread);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (randomFile != null) {
                try {
                    randomFile.close();
                } catch (IOException e1) {
                }
            }
        }
    }
	
}
