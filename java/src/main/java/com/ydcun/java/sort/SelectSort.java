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
public class SelectSort {
	public void heapSort(int[] arry){
		int temp;
		System.out.println("建堆");
		//使得树满足堆的特性，任何节点的所有子节点都小于该节点
		for(int i=arry.length/2-1;i>=0;--i){
			heapAdjust(arry,i,arry.length);
		}
		System.out.println("调整堆");
		//将根节点与树的最后一个节点交互，树节点数量递减，同时在调整堆
		for(int i=arry.length-1;i>0;i--){
			temp = arry[i];
			arry[i]=arry[0];
			arry[0]=temp;
			heapAdjust(arry, 0,i);
		}
		System.out.println("排序后");
		System.out.println(Arrays.toString(arry));
	}
	/**
	 * 调整head的节点与直接子节点，使得最大的在head位置。
	 * 当与子节点发生交互后同时使得交换后的节点与其子节点比较依次进行下去
	 * @param arry
	 * @param head
	 * @param size
	 */
	private void heapAdjust(int[] arry,int head,int size){
		int temp = arry[head];
		int child ;
		for(;head*2+1<size;head=child){
			child = head*2+1;
			if(child!=size-1 && arry[child]<arry[child+1]){
				child++;
			}
			if(arry[child]>temp){
				arry[head] = arry[child];
			}else{
				break;
			}
		}
		arry[head]=temp;
		System.out.println(Arrays.toString(arry));
	}
	
	
	
	/**
	 * 直接选择排序
	 */
	public void directSelect(int[] arry){
		int temp;
		for(int i=1;i<arry.length;i++){
			for(int j=i;j<arry.length;j++){
				if(arry[i-1]>arry[j]){
					temp = arry[i-1];
					arry[i-1] = arry[j];
					arry[j] = temp;
				}
			}
			System.out.println("1第"+i+"趟"+Arrays.toString(arry));
		}
	}

	/**
	 *  选择插入排序 
	 * @param arry 
	 */
	public void selectInsert(int[] arry){
		int temp,index;
		for(int i=1;i<arry.length;i++){
			temp = arry[i-1];
			index = i-1;
			for(int j=i;j<arry.length;j++){
				//记录比每次循环最小的值
				if(temp>arry[j]){
					temp = arry[j];
					index = j;
				}
			}
			//每一趟最多交换一次
			if(index!=i-1){
				arry[index] = arry[i-1];
				arry[i-1] = temp; 
			}
			System.out.println("2第"+i+"趟"+Arrays.toString(arry));
		}
	}
	
	@Test
	public void test(){
		int temp;
		int[] arry = {1,2,3,4,5,3,2,1,6,9};
		System.out.println("\n排序前");
		System.out.println(Arrays.toString(arry));
		//堆排序
		System.out.println("堆排序");
		heapSort(arry.clone());
		//直接选择排序
		System.out.println("直接选择排序");
		directSelect(arry.clone());
		//选择插入排序
		System.out.println("选择插入排序");
		selectInsert(arry.clone());
	}
}
