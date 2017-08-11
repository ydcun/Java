package com.ydcun.java.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Task2 {
	private Lock lock = new ReentrantLock();
	private Condition condition1 = lock.newCondition();
	private Condition condition2 = lock.newCondition();
	private Condition condition3 = lock.newCondition();
	private int i = 1;

	public void main(int j) {
		lock.lock();
		try {
			while (i != 1) {
				try {
					condition1.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 1; i <= 5; i++) {
				System.out.println("main " + Thread.currentThread() + ":" + j);
			}
			i = 2;
			condition2.signal();
		} finally {
			lock.unlock();
		}
	}

	public void sub(int j) {
		lock.lock();
		try {
			while (i != 2) {
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 1; i <= 3; i++) {
				System.out.println("sub " + Thread.currentThread() + ":" + j);
			}
			i = 3;
			condition3.signal();
		} finally {
			lock.unlock();
		}
	}

	public void sub2(int j) {
		lock.lock();
		try {
			while (i != 3) {
				try {
					condition3.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			for (int i = 1; i <= 3; i++) {
				System.out.println("sub2 " + Thread.currentThread() + ":" + j);
			}
			i = 1;
			condition1.signal();
		} finally {
			lock.unlock();
		}
	}
}

/**
 *	三个线程顺序循环执行
 */
public class ThreadThree {
	public static void main(String[] args) {
		final Task2 task = new Task2();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					task.sub(i);
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 1; i <= 50; i++) {
					task.sub2(i);
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			task.main(i);
		}
	}
}
