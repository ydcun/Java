package com.ydcun.java.proxy;

import java.util.Date;

/**
 * @author ydcunu809
 * proxy model
 * 
 * CGLIB 包可以实现使用接口生成代理类
 */
public class ProxyDemo extends IBase {
	Target target = new Target();
	@Override
	public void run() {
		System.out.println("start date:"+new Date());
		target.run();
		System.out.println("end date:"+new Date());
	}
	
	public static void main(String[] args){
		new ProxyDemo().run();
	}
}
