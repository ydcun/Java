/**
 *
 */
package com.ydcun.java.bean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

import com.ydcun.java.reflection.ReflectPoint;

/**
 * @author ydcun_home
 *
 *	javaBean  属性第二个字符小写生成的get set方法 第一个也小写
 *	setage  ->> age
 *	setAge  ->> age
 *  setAGE  ->> AGE
 */
public class IntrospectorTest {
	public static void main(String[] args) throws Exception {
		ReflectPoint pt1 = new ReflectPoint(3,4);
		String propertyName ="x";
		PropertyDescriptor pd = getProperty(pt1, propertyName);
		
		Object value =7;
		setProperties(pt1, propertyName, value);
		System.out.println(pt1.getX());

		//用apache提供的BeanUtil工具类
		System.out.println(BeanUtils.getProperty(pt1, "x").getClass().getName());
		BeanUtils.setProperty(pt1, "x","9");
		System.out.println(pt1.getX());

		//支持级联操作  类型工具会帮助你转换
		BeanUtils.setProperty(pt1, "birthday.time", "111");
		System.out.println(BeanUtils.getProperty(pt1, "birthday.time"));

		PropertyUtils.setProperty(pt1, "x",9);
		System.out.println(pt1.getX());
	}

	/**
	 * @param pt1
	 * @param propertyName
	 * @param value
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void setProperties(Object pt1, String propertyName, Object value)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd2 = new PropertyDescriptor(propertyName,pt1.getClass());
		Method methodSetX = pd2.getWriteMethod();
		methodSetX.invoke(pt1, value);
	}

	/**
	 * @param pt1
	 * @param propertyName
	 * @return
	 * @throws IntrospectionException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static PropertyDescriptor getProperty(Object pt1, String propertyName)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd = new PropertyDescriptor(propertyName,pt1.getClass());
		Method methodGetX = pd.getReadMethod();
		Object retVal = methodGetX.invoke(pt1);
		System.out.println(retVal);
		return pd;
	}
}
