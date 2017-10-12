package com.ydcun.java.interview.aiqyi;

import java.math.BigInteger;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] arege){

        Scanner sc = new Scanner(System.in);
        String[] arr = sc.nextLine().split(" ");
        Integer x2 = Integer.parseInt(arr[1]);
        Integer k2 = Integer.parseInt(arr[3]);
        StringBuffer sb1 = new StringBuffer(arr[0]);
        for(Integer i=1;i<x2;i++){
            sb1.append(arr[0]);
        }
        BigInteger x1 = new BigInteger(sb1.toString());
        sb1=null;

        StringBuffer sb2 = new StringBuffer(arr[2]);
        for(Integer i=1;i<k2;i++){
            sb2.append(arr[2]);
        }
        BigInteger k1 = new BigInteger(sb2.toString());
        sb2=null;

        if(x1.compareTo(k1)<0){
            System.out.print("Lee");
        }else if(x1.compareTo(k1)==0){
            System.out.print("Equal");
        }else{
            System.out.print("Greater");
        }
     }
}
