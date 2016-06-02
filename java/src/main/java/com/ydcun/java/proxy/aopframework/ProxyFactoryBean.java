/**
 * 
 */
package com.ydcun.java.proxy.aopframework;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ydcun_home
 *
 */
public class ProxyFactoryBean {

	private Advice advice;
	private Object target;
	public Object getProxy() {
		Object proxy = Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						advice.beforeMethod();
						Object retVal = method.invoke(target, args);
						advice.afterMethod(method);
						return retVal;
					}
				});
		return proxy;
	}
	/**  
	 * 获取advice  
	 * @return advice advice  
	 */
	public Advice getAdvice() {
		return advice;
	}
	
	/**  
	 * 设置advice  
	 * @param advice advice  
	 */
	public void setAdvice(Advice advice) {
		this.advice = advice;
	}
	
	/**  
	 * 获取target  
	 * @return target target  
	 */
	public Object getTarget() {
		return target;
	}
	
	/**  
	 * 设置target  
	 * @param target target  
	 */
	public void setTarget(Object target) {
		this.target = target;
	}
	

}


