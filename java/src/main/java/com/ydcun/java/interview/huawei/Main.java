package com.ydcun.java.interview.huawei;

import java.util.*;

public class Main {
    public static void main(String[] arge){
        Scanner sc = new Scanner(System.in);
        String[] array = sc.nextLine().split("\\|");

        quickSortSub(array,0,array.length-1);

        for(int i=0;i<array.length;i++){
            System.out.print(array[i]);
            if(i<array.length-1)
                System.out.print("|");
        }
    }
    private static void quickSortSub(String[] arry, int start, int end) {
        if(start>=end) return;
        String base = arry[start];
        int i=start,j=end+1;
        String temp;
        while(true){
            while(i<end && isSwap(arry[++i],base));
            while(j>start && !isSwap(arry[--j] , base));
            if(i<j){
                temp = arry[i];
                arry[i] = arry[j];
                arry[j] = temp;
            }else{
                break;
            }
        }
        temp = arry[j];
        arry[j] = arry[start];
        arry[start] = temp;
        quickSortSub(arry, start, j-1);
        quickSortSub(arry, j+1, end);
    }
    private static boolean isSwap(String s1,String s2){
        String[] strs1 = s1.split("\\.");
        String[] strs2 = s2.split("\\.");
        reverse(strs1);
        reverse(strs2);
        int length = strs1.length < strs2.length ? strs1.length : strs2.length;
        for (int i = 0; i < length; i++)
        {
            int cmp = strs1[i].compareTo(strs2[i]);
            if (cmp != 0)
            {
                return cmp<0;
            }
        }
        boolean b= (strs1.length - strs2.length)<0;
        return b;
    }
    public static void reverse(String[] strs){
        for (int i = 0; i < (strs.length + 1) / 2; i++)
        {
            String tmp = strs[i];
            strs[i] = strs[strs.length - i - 1];
            strs[strs.length - i - 1] = tmp;
        }
    }
}
