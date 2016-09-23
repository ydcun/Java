package com.ydcun.java.didi;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *求任意一个数组中所有和为给定数的组合个数
 */
public class Main {

    private static int count = 0;
    private static int []array;
    private static int sum;
    
    static int getCount() {
        Main.quickSort(array, 0, array.length-1);
        Main.sum(array.length-1);
        return count;
    }
    
    private  static int[] sum(int n) {
        if(n == 0){
            return new int[]{array[0]};
        }
        
        int length = (int)Math.pow(2, (n+1))-1;
        int a[] = new int[length];
        int b[] = sum(n-1);
        
        int i=0;
        for(i=0; i<(length-1)/2; i++) {
            a[i] = b[i];
            int temp = b[i] + array[n];
            if(temp == sum){
                count++;
            }
            a[i+(length+1)/2] = temp;
        }
        if(array[n] == sum) {
            count++;
        }
        a[(length-1)/2] = array[n];
        
        return a;
    }
    
    private static void quickSort(int []array, int left, int right) {
        if(left >= right) {
            return;
        }
        int q = pagenation(array, left, right);
        quickSort(array, left, q-1);
        quickSort(array, q+1, right);
    }
    
    private static int pagenation(int []array, int left, int right) {
        int i=left, j=right+1;
        int p = (int)Math.random()*(right-left+1)+left;
        swap(array,left, p);
        int x = array[left];
        
        while(true) {
            while(i<right && array[++i] < x);
            while(j>left && array[--j] > x);
            if(i > j) {
                break;
            }
            swap(array,i,j);
        }
        return j;
    }
    
    private static void swap(int [] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    
    public static void main(String[] args) throws Exception {
    	BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
		String str = strin.readLine();
		String[] arr = str.split(" ");
		String[] arrStr = strin.readLine().split(" ");
		sum = Integer.parseInt(arr[1]);
		array = new int[Integer.parseInt(arr[0])];
		for(int i=0;i<Integer.parseInt(arr[0]);i++){
			array[i] = Integer.parseInt(arrStr[i]);
		}
        System.out.println(Main.getCount());
    }
    
}
