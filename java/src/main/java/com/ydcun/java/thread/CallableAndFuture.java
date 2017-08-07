package com.ydcun.java.thread;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author ydcun_home
 *
 */
public class CallableAndFuture {
	public static void main(String[] args) {
		ExecutorService pool = Executors.newSingleThreadExecutor();

		Future<String> future = pool.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				Thread.sleep(2000);
				return "hello";
			}

		});
		System.out.println("准备");
		try {
			System.out.println(future.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("结束");

		// 提交多个任务
		ExecutorService pool2 = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(pool2);
		final Random rd = new Random();
		for (int i = 0; i < 20; i++) {
			final int seq = i;
			completionService.submit(new Callable<Integer>() {
				@Override
				public Integer call() throws Exception {
					Thread.sleep(rd.nextInt(10)*1000);
					return seq;
				}
			});
		}
		for (int i = 0; i < 20; i++) {
			try {
				System.out.println(completionService.take().get());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
