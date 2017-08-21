package com.ydcun.java.thread;

/**
 * Created by ydcun-psjs on 2017/8/11.
 */
public class Demo {
    public static void main(String[] arge){
        Integer i01 = 59;
        int i02 = 59;
        Integer i03 =Integer.valueOf(59);
        Integer i04 = new Integer(59);

        System.out.println(i01==i02);
        System.out.println(i01==i03);
        System.out.println(i03==i04);
        System.out.println(i02==i04);

    }

}
