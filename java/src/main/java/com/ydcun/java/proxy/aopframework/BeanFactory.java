/**
 * 
 */
package com.ydcun.java.proxy.aopframework;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;


/**
 * @author ydcun_home
 *
 */
public class BeanFactory {
	Properties props = new Properties();
	public BeanFactory(InputStream ips){
		try {
			props.load(ips);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Object getBean(String name) throws Exception{
		String className = props.getProperty(name);
		Class clazz = Class.forName(className);
		Object bean = clazz.newInstance();
		if(bean instanceof ProxyFactoryBean){
			ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean)bean;
			Advice advice = (Advice)Class.forName(props.getProperty(name+".advice")).newInstance();
			Object target = Class.forName(props.getProperty(name+".target")).newInstance();
			proxyFactoryBean.setAdvice(advice);
			proxyFactoryBean.setTarget(target);
			Object proxy = proxyFactoryBean.getProxy();
			return proxy;
		}
		return bean;
	}
}
