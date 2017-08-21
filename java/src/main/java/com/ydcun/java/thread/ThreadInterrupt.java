package com.ydcun.java.thread;

/**
 * Created by ydcun-pro on 2017/7/12.
 */
public class ThreadInterrupt extends Thread{
    public void run(){

        for(int i=0;i<50000;i++){
            if(this.interrupted()){
                break;
            }
            System.out.println(i);
        }
    }
    public static void main(String[] arge) throws InterruptedException {
        Thread.currentThread().interrupt();
        System.out.println(Thread.interrupted());
        System.out.println(Thread.interrupted());


        ThreadInterrupt ti = new ThreadInterrupt();
        ti.start();
        Thread.sleep(100);
        ti.interrupt();
        System.out.println(ti.interrupted());
        System.out.println(ti.interrupted());

    }
}
