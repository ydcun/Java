/**
 * 
 */
package com.ydcun.java.interview.youdao;

import java.util.Scanner;

/**
 * @author ydcun-psjs
 *
 */
class Test1 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int count = new Integer(in.nextLine());
		String t = "";
		for(int i=0;i<count;i++){
			String str = in.nextLine();
			String[] array = str.split(" ");
			int n = new Integer(array[0]);
			int k = new Integer(array[1]);
			
			String temp = in.nextLine();
			String[] arrayStr = temp.split(" ");
			m(arrayStr,n,k);
			String tempT = "";
			for(int j=0;j<2*n;j++){
//				System.out.print(arrayStr[j]+" ");
				tempT= tempT+" "+arrayStr[j];
			}
			tempT = tempT.trim();
			t=t+"\n"+tempT;
		}
		
		System.out.println(t.substring(1,t.length()-1));
	}
	public static  void m(String[] array,int n,int k){
		for(int i=0;i<k;i++){
			m2(array,n);
		}
	}
	public static void m2(String[] array,int n){
		String[] temp = array.clone();
		for(int i=0;i<n;i++){
			temp[i*2] = array[2*n-1-i];
			temp[i*2+1] = array[n-1-i];
		}
		for(int i=0;i<2*n;i++){
			array[2*n-i-1] = temp[i];
		}
	}
}
