/**
 * 
 */
package com.ydcun.java.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ydcun_home
 * JVM　创建代理类
 * 
 * 1.首先通过字节码获取构造方法和方法列表
 * 2.通过字节码生成对象
 * 
 * InvocationHandler 中的invoke方法有三个参数：代理对象 ，方法名，方法参数
 */
public class ProxyTest {
	public static void getClassInfo(){

		Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		System.out.println(clazzProxy1.getName());
		
		System.out.println("----------begin constructor list---------");
		// 获取字节码的构造方法
		Constructor[] constructors = clazzProxy1.getConstructors();
		for(Constructor constructor:constructors){
			String name = constructor.getName();
			StringBuilder sBuidler = new StringBuilder(name);
			sBuidler.append('(');
			Class[] clazzParams = constructor.getParameterTypes();
			for(Class clazzParam:clazzParams){
				String typeName = clazzParam.getName();
				sBuidler.append(typeName).append(',');
			}
			if(sBuidler.toString().lastIndexOf(',')>-1){
				sBuidler.deleteCharAt(sBuidler.length()-1);
			}
			sBuidler.append(')');
			System.out.println(sBuidler.toString());
		}
		System.out.println("----------end constructor list---------");
		
		System.out.println("----------begin methods list---------");
		// 获取字节码的构造方法
		 Method[] Methods = clazzProxy1.getMethods();
		for(Method method:Methods){
			String name = method.getName();
			StringBuilder sBuidler = new StringBuilder(name);
			sBuidler.append('(');
			Class[] clazzParams = method.getParameterTypes();
			for(Class clazzParam:clazzParams){
				String typeName = clazzParam.getName();
				sBuidler.append(typeName).append(',');
			}
			if(sBuidler.toString().lastIndexOf(',')>-1){
				sBuidler.deleteCharAt(sBuidler.length()-1);
			}
			sBuidler.append(')');
			System.out.println(sBuidler.toString());
		}
		System.out.println("----------end methods list---------");
	}
	public static void main(String[] args) throws Exception {
		getClassInfo();
		
		//创建代理类

		System.out.println("---------------1--------------------");
		Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
//		clazzProxy1.newInstance();//没有无参的构造方法
		Constructor constructor = clazzProxy1.getConstructor(InvocationHandler.class);
		class MyInvocationHandler1 implements InvocationHandler{

			/* (non-Javadoc)
			 * @see java.lang.reflect.InvocationHandler#invoke(java.lang.Object, java.lang.reflect.Method, java.lang.Object[])
			 */
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}
			
		}
		Collection proxy1 = (Collection)constructor.newInstance(new MyInvocationHandler1());
		System.out.println(proxy1.toString());
		proxy1.clear();
		//System.out.println(proxy1.size());//会出错 因为：MyInvocationHandler1类中invoke方法返回值为null 第三种方法中有例子
		
		
		System.out.println("---------------2---------------------");
		Collection proxy2 = (Collection)constructor.newInstance(new InvocationHandler(){
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				// TODO Auto-generated method stub
				return null;
			}});
		

		System.out.println("---------------3--------------------");
		Collection proxy3 = (Collection)Proxy.newProxyInstance(
				Collection.class.getClassLoader(), 
				new Class[]{Collection.class}, 
				new InvocationHandler() {
					ArrayList target = new ArrayList();
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						long beginTime = System.currentTimeMillis();
						Object retVal = method.invoke(target, args);
						long endTime = System.currentTimeMillis();
						System.out.println(method.getName()+"running time of"+(endTime-beginTime));
//						return method.invoke(proxy, args);//会出现死循环
						return retVal;
					}
				});
		Object o = proxy3.add("ydcun");
		System.out.println(o);
		System.out.println(proxy3.size());
		
		
		System.out.println("-----------------4---------------");

		ArrayList target = new ArrayList();
		Collection proxy4 = (Collection)getProxy(target,new MyAdvice());
		Object o1 = proxy4.add("ydcun");
		System.out.println(o1);
		System.out.println(proxy4.size());
	}
	/**
	 * @param target
	 * @return
	 */
	private static Object getProxy(final Object target,final Advice advice) {
		Object proxy4 = Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				new InvocationHandler() {
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable {
						advice.beforeMethod();
						Object retVal = method.invoke(target, args);
						advice.afterMethod(method);
//						return method.invoke(proxy, args);//会出现死循环
						return retVal;
					}
				});
		return proxy4;
	}
}
/**
 * @author ydcun_home
 * 接口统一契约或建议，在invoke中执行指定的动作 
 */
interface Advice{
	public void afterMethod(Method method);
	public void beforeMethod();
}
class MyAdvice implements Advice{
	long beginTime;
	public void afterMethod(Method method) {
		System.out.println("end");
		long endTime = System.currentTimeMillis();
		System.out.println(method.getName()+"running time of"+(endTime-beginTime));
	}
	public void beforeMethod() {
		System.out.println("start");
		beginTime = System.currentTimeMillis();
	}
	
}
