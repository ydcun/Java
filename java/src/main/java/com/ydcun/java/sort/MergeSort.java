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
public class MergeSort {
	@Test
	public void test(){
		int temp;
		int[] arry = {1,2,3,4,5,3,2,1,6,9};
		System.out.println("\n排序前");
		System.out.println(Arrays.toString(arry));
		mergeSort(arry);
	}

	/**
	 * 归并排序
	 */
	private void mergeSort(int[] arry) {
		mergeSortSub(arry, 0, arry.length-1);
	}
	private void mergeSortSub(int[] arry, int start, int end) {
		if(start>=end) return;
		int mid = (start+end)/2;
		mergeSortSub(arry, start, mid);
		mergeSortSub(arry, mid+1, end);

		System.out.println("start:"+start+" end:"+end);
		int temp,index=0;
		int i=start,j=mid+1;
		int[] tempArry = new int[arry.length];
		while(i<=mid && j<=end){
			if(arry[i]>arry[j]){
				tempArry[index]=arry[j++];
			}else{
				tempArry[index] = arry[i];
				i++;
			}
			index++;
		}
		while(i<=mid){
			tempArry[index]=arry[i];
			i++;
			index++;
		}
		while(j<=end){
			tempArry[index]=arry[j];
			j++;
			index++;
		}
		for(index=0,i=start;i<=end;i++,index++){
			arry[i] = tempArry[index];
		}
		System.out.println(Arrays.toString(arry));
	}
}
