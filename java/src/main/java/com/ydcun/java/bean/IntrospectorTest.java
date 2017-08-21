/**
 *
 */
package com.ydcun.java.bean;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

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

		//用apache提供的BeanUtil工具类（commons-beanutils  commons-logging的支持）
		System.out.println(BeanUtils.getProperty(pt1, "x").getClass().getName());
		BeanUtils.setProperty(pt1, "x","9");//bean中的八种数据类型方法传入的是字符串是可以自动给转换的
		System.out.println(pt1.getX());
		
		
//		BeanUtils.setProperty(pt1, "birthday","2016-07-30");//不能将非基本数据类型自动转换

		/*//注册转换器  方法一
		 ConvertUtils.register(new Converter() {
			SimpleDateFormat sb = new SimpleDateFormat("yyyy-MM-dd");
			@Override
			public Object convert(Class type, Object value) {
				if(value==null){
					return null;
				}
				if(!(value instanceof String)){
					throw new ConversionException("not convert, only String type");
				}
				String str = (String)value;
				if(str.trim().equals("")){
					return null;
				}
				Date d = null;
				try {
					d = sb.parse((String)value);
				} catch (ParseException e) {
					e.printStackTrace();
					throw new RuntimeException(e);//异常链不能断
				}
				return d;
			}
		}, Date.class);*/
		//注册转换器  方法二
		ConvertUtils.register(new DateLocaleConverter(),Date.class);//BeanUtils提供了一种实现  传入字符串是空串的时候会报错
		BeanUtils.setProperty(pt1, "birthday","2016-07-30");
		System.out.println(pt1.getBirthday().toString());
		
		//支持级联操作  类型工具会帮助你转换
		BeanUtils.setProperty(pt1, "birthday.time", "111");
		System.out.println(BeanUtils.getProperty(pt1, "birthday.time"));

		PropertyUtils.setProperty(pt1, "x",9);
		System.out.println(pt1.getX());
		
		
		//beanutil填充
		Map<String,Object> map = new HashMap();
		Map<String,Object> subMap = new HashMap();
		subMap.put("birthday","1991-04-06");
		map.put("birthday", "2016-07-30");
		map.put("p", subMap);
		//对ReflectPoint类中的ReflectPoint属性进行注册转换器
		ConvertUtils.register(new Converter() {
			
			public Object convert(Class type, Object value) {
				if(value==null){
					return null;
				}
				if(!(value instanceof Map)){
					throw new ConversionException("not convert, only Map type");
				}
				Map map = (Map)value;
				ReflectPoint p = new ReflectPoint();
				try {
					BeanUtils.setProperty(p,"birthday", map.get("birthday"));
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return p;
			}
		}, ReflectPoint.class);
		BeanUtils.populate(pt1, map);
		System.out.println("F"+pt1.getBirthday());
		System.out.println("sub"+pt1.getP().getBirthday());
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
