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
public class BucketSort {
	@Test
	public void test(){
		int temp;
		int[] arry = {1,2,3,4,5,3,2,1,9,6};
		System.out.println("\n排序前");
		System.out.println(Arrays.toString(arry));
		//桶排序
		bucketSort(arry,1,9);
	}

	/**
	 * 桶排序
	 */
	private void bucketSort(int[] arry,int min,int max) {
		int[] buckets = new int[max-min+1];
		for(int i=0;i<arry.length;i++){
			buckets[arry[i]-min]++;
		}
		System.out.println("buckets:\t"+Arrays.toString(buckets));
		//每个buckets数组元素的值小于，等于落入当前桶中元素的个数。也就是说落入当前桶中的元素在有序序列中应该排在buckets数组元素值所确定的位置
		for(int i=1;i<buckets.length;i++){
			buckets[i] +=buckets[i-1];
		}
		System.out.println("new buckets:\t"+Arrays.toString(buckets));
		int[] tempArry = arry.clone();
		//根据buckets 将数据对号入座
		for(int i= arry.length-1;i>=0;i--){
			arry[--buckets[tempArry[i]-min]] = tempArry[i];
			System.out.println("i:"+i+" "+Arrays.toString(arry)+"tempArry[i]-min"+(tempArry[i]-min));
		}
		System.out.println(Arrays.toString(arry));
	}
}
