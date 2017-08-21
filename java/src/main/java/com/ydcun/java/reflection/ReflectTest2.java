/**
 * 
 */
package com.ydcun.java.reflection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;


/**
 * @author ydcun_home
 *
 */
public class ReflectTest2 {
	public static void main(String[] args) throws Exception {
//		Collection collections = new ArrayList();
		//先计算hashcode的值选择防治的区域 之后再比较equals 
		//对象被存储到hash集合后，不要修改与hashcode计算相关的值
		Collection collections = new HashSet();
		ReflectPoint pt1 = new ReflectPoint(3,3);
		ReflectPoint pt2 = new ReflectPoint(5,5);
		ReflectPoint pt3 = new ReflectPoint(3,3);
		collections.add(pt1);
		collections.add(pt2);
		collections.add(pt3);
		collections.add(pt1);
		collections.add(pt1);
		pt1.y=1111;//修改了就remove不了了  hash值没了
		collections.remove(pt1);
		System.out.println(collections.size());
		
		
		//用反射来实现集合  可以回写配置文件
		InputStream ips = new FileInputStream(ReflectTest2.class.getResource("").getPath()+"/config.properties");
		Properties props = new Properties();
		props.load(ips);
		ips.close();//将通道关了
		String className = props.getProperty("classname");
		Collection collections3 = (Collection)Class.forName(className).newInstance();
		collections3.add(pt1);
		collections3.add(pt2);
		collections3.add(pt3);
		collections3.add(pt1);
		collections3.add(pt1);
		System.out.println(collections3.size());
		
		
		//用类加载器顺便加载配置文件   读取参数比较好
//		InputStream ips2 = ReflectTest2.class.getClassLoader().getResourceAsStream("com/ydcun/java/reflection/config.properties");
		InputStream ips2 = ReflectTest2.class.getResourceAsStream("config.properties");
		Properties props2 = new Properties();
		props2.load(ips2);
		ips2.close();//将通道关了
		String className2 = props.getProperty("classname");
		System.out.println(className2);
	}
}
