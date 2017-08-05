package com.ydcun.java.thread;

/**
 * Created by ydcun-pro on 2017/7/12.
 */
public class CurrentThread extends Thread{
    public CurrentThread(){
        System.out.println("CurrentThread start");
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.getName());
        System.out.println("CurrentThread end");
    }
    public void run(){
        System.out.println("run start");
        System.out.println(Thread.currentThread().getName());
        System.out.println(this.getName());
        System.out.println("run end");
    }
    public static void main(String[] arge){
        CurrentThread c = new CurrentThread();
        Thread t1 = new Thread(c);
        t1.setName("A");
        t1.start();
    }
}
