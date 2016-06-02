package com.ydcun.java.proxy;

import java.util.Date;

/**
 * @author ydcunu809
 * proxy model
 * 
 * CGLIB lib
 */
public class Proxy extends IBase {
	Target target = new Target();
	@Override
	public void run() {
		System.out.println("start date:"+new Date());
		target.run();
		System.out.println("end date:"+new Date());
	}
	
	public static void main(String[] args){
		new Proxy().run();
	}
}
