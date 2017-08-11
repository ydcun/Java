/**
 * 
 */
package com.ydcun.java.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.junit.Test;

import com.ydcun.java.reflection.ReflectPoint;

/**
 * @author ydcun-psjs
 *	使用内省API操作bean属性()
 */
public class Demo1 {
	
	//得到bean的所有属性
	@Test
	public void test1() throws Exception{
		BeanInfo beanInfo = Introspector.getBeanInfo(ReflectPoint.class,Object.class);//获取所有属性包括父类的 去除Object的属性
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd : pds){
			System.out.println(pd.getName());
		}
	}
	//通过PropertyDescriptor 获取属性的 get set方法
	@Test
	public void test2() throws Exception{
		ReflectPoint p = new ReflectPoint();
		PropertyDescriptor pd = new PropertyDescriptor("Y", ReflectPoint.class);
		Method setAge = pd.getWriteMethod();
		setAge.invoke(p,3);
		System.out.println(p.getY());
		System.out.println("参数类型"+pd.getPropertyType());
	}
}
