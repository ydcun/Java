package com.ydcun.java.proxy.aopframework;

import java.lang.reflect.Method;

/**
 * @author ydcun_home
 * 接口统一契约或建议，在invoke中执行指定的动作 
 */
interface Advice{
	public void afterMethod(Method method);
	public void beforeMethod();
}