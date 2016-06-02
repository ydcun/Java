package com.ydcun.java.classloader;

import java.util.Date;

/**
 * @author ydcunu809
 * 三个主要的类加载器：	BootStrap, 不是java类是c++写的   jre/lib/rt.jar
 * 					ExtClassLoader, 			   jre/lib/ext/*.jar
 * 					AppClassLoader				   classpath指定的所有jar或目录
 * 
 * 自己写的类加载器必须继承ClassLoader  并指定父类loader
 * 为了防止出现多份字节码，类加载器的委托机制优先让父类加载器来加载。如果自己实现一个类加载器必须有特殊的方法
 * 
 * 如果将自动编译的class文件删除就会在自定义的类加载器下面找，类加载的委托机制
 * 		实验步骤：1. 先将ClassLoaderAttachment.class进行加密放到resources目录下
 * 				2. 将加密的文件替换原来的class文件
 * 				3. 通过自定义类加载加载，发现出错，原因是父类会加载自己的class文件而class已经加密了
 * 				4. 删除自动生成目录下的class文件，在运行正常加载
 */
public class ClassLoaderTest1 {
	public static void main(String[] arge) throws Exception{
		System.out.println(ClassLoaderTest1.class.getClassLoader().getClass().getName()); //sun.misc.Launcher$AppClassLoader
		System.out.println(System.class.getClassLoader());//特殊的类加载器加载的   打印 null

		/**
		 * 	sun.misc.Launcher$AppClassLoader
			sun.misc.Launcher$ExtClassLoader
			null
		 * **/
		ClassLoader loader = ClassLoaderTest1.class.getClassLoader();
		while(loader!=null){
			System.out.println(loader.getClass().getName());
			loader = loader.getParent();
		}
		System.out.println(loader);
		
//		System.out.println(new ClassLoaderAttachment().toString());
		
		/*如果将自动编译的class文件删除就会在自定义的类加载器下面找，类加载的委托机制*/
		Class clazz = new MyClassLoader("/home/ydcunu809/git/java/src/main/resources").loadClass("com.ydcun.java.classloader.ClassLoaderAttachment");
		Date d1 = (Date)clazz.newInstance();
		System.out.println(d1); 
		//ClassLoaderAttachment n=new ClassLoaderAttachment();
	}
}
