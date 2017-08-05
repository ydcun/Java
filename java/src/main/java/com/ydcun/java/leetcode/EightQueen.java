package com.ydcun.java.leetcode;

import java.util.Arrays;

/**
 * 八皇后问题  92种情况
 * Created by ydcun-pro on 2017/8/5.
 */
public class EightQueen {
    static int sum = 0;
    public static void main(String[] arge){
        int[] array = new int[8];

        eightQueen(array,0);
        System.out.println("sum:"+sum);
    }
    public static void eightQueen(int[] array,int t){
        for(int i=0;i<array.length;i++){
            array[t]=i;
            if(check(array,t)){
                if(t==array.length-1) {
                    myPrint(array);
                    sum++;
                }else {
                    eightQueen(array, t + 1);
                }
            }
        }
    }
    public static boolean check(int[] array,int k){
        for(int i=0;i<k;i++){
            if(Math.abs(i-k)==Math.abs(array[i]-array[k]) || array[i]==array[k]){
                return false;
            }
        }
        return true;
    }
    public static void myPrint(int[] array){
        System.out.println(Arrays.toString(array));
        for(int i=0;i<array.length;i++){
            for(int j=0;j<array.length;j++){
                if(j==array[i]) {
                    System.out.print("1 ");
                }else{
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
