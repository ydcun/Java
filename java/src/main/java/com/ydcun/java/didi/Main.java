package com.ydcun.java.didi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 *求任意一个数组中所有和为给定数的组合个数
 */
public class Main {

    private static int count = 0;
    private static int []array;
    private static int sum;
    
    static int getCount() {
        Arrays.sort(array);
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
        int temp;
        for(i=0; i<(length-1)/2; i++) {
            a[i] = b[i];
            temp = b[i] + array[n];
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
		arrStr=null;
		System.gc();
        System.out.println(Main.getCount());
    }
    
}
