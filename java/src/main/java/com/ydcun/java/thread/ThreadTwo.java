package com.ydcun.java.thread;

class Task {
	private boolean isSubThread = true;
	public synchronized void sub(int j) {
		while (!isSubThread) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 1; i <= 3; i++) {
			System.out.println("sub " + Thread.currentThread() + ":" + j);
		}
		isSubThread = false;
		this.notify();
	}

	public synchronized void main(int j) {
		while(isSubThread) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 1; i <= 5; i++) {
			System.out.println("main " + Thread.currentThread() + ":" + j);
		}
		isSubThread = true;
		this.notify();
	}
}

/**
 *  子线程与主线程交替执行50次
 */
public class ThreadTwo {
	public static void main(String[] args) {
		final Task task = new Task();
		new Thread(new Runnable(){

			public void run() {
				for (int i = 1; i <= 50; i++) {
					task.sub(i);
				}
			}
		}).start();
		for (int i = 1; i <= 50; i++) {
			task.main(i);
		}
	}
}