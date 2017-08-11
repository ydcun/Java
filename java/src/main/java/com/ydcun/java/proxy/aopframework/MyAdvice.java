package com.ydcun.java.proxy.aopframework;

import java.lang.reflect.Method;

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