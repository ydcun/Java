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
		int[] array = {1,2,3,4,5,3,2,1,6,9};
		System.out.println("\n排序前");
		System.out.println(Arrays.toString(array));
		//直接插入排序
		System.out.println("直接插入排序");
		directInster(array.clone());
		//折半插入排序
		System.out.println("折半插入排序");
		binaryInster(array.clone());
		//shell排序
		System.out.println("shell排序");
		shellSort(array.clone());
	}

	/**
	 * 	直接插入排序
	 */
	private void directInster(int[] array) {
		int temp;
		for(int i=1;i<array.length;i++){
			for(int j=i;j>0;j--){
				if(array[j-1]>array[j]){
					temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
					System.out.println("1");
				}
			}
			System.out.println("第"+i+"趟"+Arrays.toString(array));
		}
	}
	/**
	 * 折半插入排序
	 */
	public void binaryInster(int[] array){
		int temp;
		for(int i=1;i<array.length;i++){
			temp = array[i];
			//找插入位置
			int start = 0;
			int end = i-1;
			int mid;
			while(end>=start){
				mid = (start+end)/2;
				if(temp > array[mid]){
					start = mid+1;
				}else{
					end = mid-1;
				}
			}
			for(int j=i;j>start;j--){
				array[j] = array[j-1];
			}
			array[start] = temp;
			System.out.println("第"+i+"趟"+Arrays.toString(array));
		}
	}
	
	/**
	 * shell排序
	 */
	public void shellSort(int[] array) {
		int h = 1, temp;
		// 得到增量序列的最大值
		while (h <= array.length / 3)
			h = h * 3 + 1;
		while (true) {
			for (int i = 0; i < h; i++) {
				for (int j = i; j + h < array.length; j += h) {
					if (array[j] > array[j + h]) {
						temp = array[j];
						array[j] = array[j + h];
						array[j + h] = temp;
					}
				}
			}
			if (h == 1) {
				break;
			}
			h--;
		}
		System.out.println(Arrays.toString(array));
	}
}
