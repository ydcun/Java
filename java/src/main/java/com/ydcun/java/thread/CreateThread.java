/**
 * 
 */
package com.ydcun.java.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 创建线程的三种方法
 * 
 * 1. Runnable子类
 * 2. Thread子类
 * 3. Callable子类  FutureTask<E>封装  传入Thread中执行
 *
 */
public class CreateThread {
	public static void main(String[] args) throws Exception {
		//创建线程的第一种方法
		new Thread(new Runnable() {

			public void run() {
				for (int i = 0; i < 100; i++) {
					System.out.print(1);
				}
				System.out.println();
			}
		}).start();
		
		//创建线程的第二种方法
		new MyThread().start();
		
		//创建线程的第三种方法
		List<FutureTask<Integer>> list = 
				new ArrayList<FutureTask<Integer>>();
		for(int i=0;i<100;i++){
			MyThread2 myThread2 = new MyThread2();
			FutureTask<Integer> ft  = new FutureTask<Integer>(myThread2);
			myThread2.value = i;
			new Thread(ft).start();
			list.add(0,ft);
		}
		for(FutureTask<Integer> ft:list){
			System.out.println(ft.get());
		}
	}
}
class MyThread extends Thread{
	@Override
	public void run() {
		for(int i=0;i<100;i++){
			System.out.print(2);
		}
		System.out.println();
	}
	
}
class MyThread2 implements Callable<Integer>{
	public Integer value=null;
	Random rd = new Random();

	public Integer call() throws Exception {
		Thread.sleep(rd.nextInt(100)*10);
		return value;
	}
	
}
