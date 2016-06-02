package com.ydcun.java.classloader;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author ydcunu809
 * 通过覆盖findClass方法来实现
 * 其中loadeClass 通过委托机制先调用父类加载之后在调用findClass方法
 * 
 * 1. 先将自己加密的class类替换原来的class类，运行会报错 
 * 		Exception in thread "main" java.lang.ClassFormatError: Incompatible magic value 889275713 in class file
 * 2. 用自己编写的类加载器进行加载 
 */
public class MyClassLoader extends ClassLoader {
	public static void main(String[] args) throws Exception{
		/*/home/ydcunu809/git/java/target/classes/com/ydcun/java/classloader/ClassLoaderAttachment.class /home/ydcunu809/git/java/src/main/resources*/
		String srcPath = args[0];
		String destDir= args[1];
		FileInputStream fis = new FileInputStream(srcPath);
		String destFileName = srcPath.substring(srcPath.lastIndexOf("/")+1);
		String destPath = destDir+"/"+ destFileName;
		FileOutputStream fos = new FileOutputStream(destPath);
		cypher(fis,fos);
		fos.close();
		fis.close();
	}
	/**
	 * 对class文件进行加密解密的方法
	 * @param ips
	 * @param ops
	 * @throws Exception
	 */
	public static void cypher(InputStream ips,OutputStream ops) throws Exception{
		int b = -1;
		while((b=ips.read())!=-1){
			ops.write(b ^ 0xff);
		}
	}
	private String classDir;
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException{
		String classFileName = classDir+"/"+name.substring(name.lastIndexOf('.')+1)+".class";
		FileInputStream fis = null;
		try{
			fis = new FileInputStream(classFileName);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			cypher(fis,bos);
			fis.close();
			byte[] bytes = bos.toByteArray();
			return defineClass(bytes,0,bytes.length);
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.findClass(name);
	}
	public MyClassLoader(){}
	public MyClassLoader(String classDir){
		this.classDir = classDir;
	}
}
