/**
 * 
 */
package com.ydcun.java.toutiao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author ydcun-psjs
 *
 */
public class main {
	public static void main(String[] args) throws Exception {
		BufferedReader strin = new BufferedReader(new InputStreamReader(System.in));
		String str = strin.readLine();
		String[] arr = str.split(" ");
		int n = Integer.parseInt(arr[0]);
		int m = Integer.parseInt(arr[1]);
		int temp1;
		int temp2;
		int count=0;
		String[] arr2 = strin.readLine().split(" ");
		for(int i=0;i<n;i++){
			temp1 = Integer.parseInt(arr2[i]);
			for(int j=i+1;j<n;j++){
				temp2 = Integer.parseInt(arr2[j]);
				if((temp1^temp2)>m){count ++;}
			}
		}
		System.out.println(count);
	}
}
