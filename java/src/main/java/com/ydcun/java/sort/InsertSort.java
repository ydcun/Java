/**
 * 
 */
package com.ydcun.java.sort;

import java.util.Arrays;

import org.junit.Test;

/**
 * @author ydcun-psjs
 *
 */
public class InsertSort {
	@Test
	public void test(){
		int temp;
		int[] arry = {1,2,3,4,5,3,2,1,6,9};
		System.out.println("\n排序前");
		System.out.println(Arrays.toString(arry));
		//直接插入排序
		System.out.println("直接插入排序");
		directInster(arry.clone());
		//折半插入排序
		System.out.println("折半插入排序");
		binaryInster(arry.clone());
		//shell排序
		System.out.println("shell排序");
		shellSort(arry.clone());
	}

	/**
	 * 	直接插入排序
	 */
	private void directInster(int[] arry) {
		int temp;
		for(int i=1;i<arry.length;i++){
			for(int j=i;j>0;j--){
				if(arry[j-1]>arry[j]){
					temp = arry[j-1];
					arry[j-1] = arry[j];
					arry[j] = temp;
					System.out.println("1");
				}
			}
			System.out.println("第"+i+"趟"+Arrays.toString(arry));
		}
	}
	/**
	 * 折半插入排序
	 */
	public void binaryInster(int[] arry){
		int temp;
		for(int i=1;i<arry.length;i++){
			temp = arry[i];
			//找插入位置
			int start = 0;
			int end = i-1;
			int mid;
			while(end>=start){
				mid = (start+end)/2;
				if(temp > arry[mid]){
					start = mid+1;
				}else{
					end = mid-1;
				}
			}
			for(int j=i;j>start;j--){
				arry[j] = arry[j-1];
			}
			arry[start] = temp;
			System.out.println("第"+i+"趟"+Arrays.toString(arry));
		}
	}
	
	/**
	 * shell排序
	 */
	public void shellSort(int[] arry){
		int h = 1,temp;
		//得到增量序列最大值
		while(h<=arry.length/3) h = h*3+1;
		while(h>0){
			System.out.println("h="+h);
			for(int i = h;i<arry.length;i++){
				temp = arry[i];
				int j = i-h;
				for(;j>=0&& arry[j]>temp;j-=h){
					arry[j+h] = arry[j];
				}
				arry[j+h] = temp;
			}
			System.out.println(Arrays.toString(arry));
			h = (h-1)/3;
		}
		
	}
}
