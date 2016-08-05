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
		directInster(arry);
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
}
