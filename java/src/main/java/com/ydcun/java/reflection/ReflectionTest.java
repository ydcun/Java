/**
 * 
 */
package com.ydcun.java.reflection;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author ydcun_home
 *	基本数据类型：boolean, byte, char, short, int, long, float, dobule
 *          Boolean.TYPE ....封装类型的TYPE常量代表对应的拆装类型
 *          
 *  1. 反射就是将类中各种元素翻译成对应java类的过程
 *  2. class类 是反射的基石
 *  3. field类的成员变量
 *  4. method 类方法    动态获取类
 */
public class ReflectionTest {
	public static void main(String[] args) throws Exception {
		String str1 = "abc";
		String str2 = new String("dfg");
		System.out.println(str1.getClass()==str2.getClass());
		System.out.println(int.class.isPrimitive());//false 不是基础数据类型
		System.out.println(Integer.class.isPrimitive());
		System.out.println(Integer.TYPE.isPrimitive());//public static final Class TYPE = Class.getPrimitiveClass("int");
		System.out.println(int[].class.isArray());//数组类型
		
		//new String(new StringBuffer("123"));  //用反射实现
		Constructor constructor = String.class.getConstructor(StringBuffer.class);
		String c1 = (String)constructor.newInstance(new StringBuffer("123"));
		System.out.println(c1);
		String c2 = String.class.newInstance();//无参的构造方法clas类直接给实现了
		
		//field 类的成员变量   获取public属性的值
		ReflectPoint pt1 = new ReflectPoint(3,5);
//		Field fieldY = pt1.getClass().getField("y");//获取公共的成员属性
		Field fieldY = pt1.getClass().getDeclaredField("y");
		int y = (Integer)fieldY.get(pt1);//获取某个对象对应的值
		System.out.println(y);

		Field fieldX = pt1.getClass().getDeclaredField("x");
		fieldX.setAccessible(true);
		int x = (Integer)fieldX.get(pt1);//获取某个对象对应的值
		System.out.println(x);
		
		//将ReflecPoint类型的对象string属性值b换成a
		changeStringValue(pt1);
		System.out.println(pt1);
		
		
		//method
		Method methodCharAt = String.class.getMethod("charAt",int.class);
//		Method methodCharAt = String.class.getDeclaredMethod("charAt",int.class);
		char char1 = (Character)methodCharAt.invoke(str1,1);//第一个参数是null对应的是静态方法
		System.out.println(char1); 
		char char2 = (Character)methodCharAt.invoke(str1,new Object[]{2});//第一个参数是null对应的是静态方法
		System.out.println(char2);
		
		//调用main方法 
		ReflectPoint.main(new String[]{"1","2"});
		//用反射的方法调用main 
		String startingClassName = "com.ydcun.java.reflection.ReflecPoint";
		Method mainMethod = Class.forName(startingClassName).getMethod("main",String[].class);
		mainMethod.invoke(null, new Object[]{new String[]{"11","2ccc34"}});//为了兼容jdk1.4会将数组打开所以会报错
		mainMethod.invoke(null, (Object)new String[]{"11","2ccc34"});//为了兼容jdk1.4会将数组打开所以会报错
		
		
		//数组反射
		int[] a1 = new int[3];
		int[] a2 = new int[4];
		int[][] a3 = new int[2][3];
		String[] a4 = new String[]{"1","2","3"};
		System.out.println(a1.getClass() == a2.getClass());
		System.out.println(a1.getClass() == a3[0].getClass());
//		System.out.println(a1.getClass() == a4.getClass());
		System.out.println(a1.getClass().getName());
		System.out.println(a1.getClass().getSuperclass().getName());
		System.out.println(a2.getClass().getSuperclass().getName());
		
		Object aObjec1 =a1;
		Object aObject2 =a2;
//		Object[] aObject3 = a1;//过不去int是基本数据类型
		Object aObject4 = a3;
		Object aObject5 = a4;
		
		System.out.println(a1);
		System.out.println(a4);
		System.out.println(Arrays.asList(a1));//asList接受的是object[]数组不能将int[]拆开
		System.out.println(Arrays.asList(a4));//String可以拆开
		
		//反射中的Array
		printObject(a1);
		printObject("ddd");
		printObject(new Object[]{"sdfd",123,12.3});
	}
	private static void printObject(Object obj) {
		//不能得到得到数组的类型，只能获取某个元素类型
		Class clazz = obj.getClass();
		if(clazz.isArray()){
			int len = Array.getLength(obj);
			for(int i=0;i<len;i++){
				System.out.println(Array.get(obj, i));
				System.out.println(Array.get(obj, i).getClass().getTypeName());
			}
		}else{
			System.out.println(obj);
		}
	}
	//将对象String类型属性值b换成a
	private static void changeStringValue(Object obj) throws Exception {
		Field[] fields = obj.getClass().getFields();
		for(Field field:fields){
			if(field.getType()==String.class){//用== 因为比较的是同一份字节码
				String strValue = (String)field.get(obj);
				String newStrValue = strValue.replaceAll("b","a");
				field.set(obj, newStrValue);
			}
		}
	}
}	
