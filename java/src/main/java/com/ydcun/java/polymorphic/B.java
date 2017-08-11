package com.ydcun.java.polymorphic;

/**
 * Created by ydcun-pro on 2017/8/8.
 */
public class B extends A{
    public void a(){}
    public void a(int a){}
    protected void a(int b,int a){}


    public static void main(String[] arges) throws Exception{
        Thread t = new Thread(new Runnable() {
            public void run() {
                System.out.println("A");
            }
        });
        t.start();
        Thread.sleep(2000);
        t.start();
    }
}
interface M1{}
interface  M2{}
interface M3 extends M1,M2{

}