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
public class SwapSort {
	@Test
	public void test(){
		int temp;
		int[] arry = {1,2,3,4,5,3,2,1,6,9,1};
		System.out.println("\n排序前");
		System.out.println(Arrays.toString(arry));
		//冒泡排序
		System.out.println("冒泡排序");
		bubbleSort(arry);
		//快速排序
		System.out.println("快速排序");
		quickSort(arry);
	}

	/**
	 * 冒泡排序
	 */
	public void bubbleSort(int[] arry) {
		int temp;
		for(int i=1;i<arry.length;i++){
			for(int j=0;j<arry.length-i;j++){
				if(arry[j]>arry[j+1]){
					temp = arry[j+1];
					arry[j+1] = arry[j];
					arry[j] = temp;
				}
			}
			System.out.println("第"+i+"趟"+Arrays.toString(arry));
		}
	}
	
	public void quickSort(int[] arry){
		int start = 0;
		int end = arry.length-1;
		quickSortSub(arry,start,end);
		System.out.println(Arrays.toString(arry));
	}

	/**
	 * 快速排序递归单元
	 * @param arry
	 * @param start
	 * @param end
	 */
	private void quickSortSub(int[] arry, int start, int end) {
		if(start>end) return;
		int base = arry[start];
		int i=start,j=end+1,temp;
		while(i<end && arry[++i] >=base);
		while(j>start && arry[--j] <= base);
		while(true){
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
		System.out.println(Arrays.toString(arry));
		quickSortSub(arry, start, j-1);
		quickSortSub(arry, j+1, end);
	}
}
