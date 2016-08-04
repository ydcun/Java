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
public class HeapSort {
	@Test
	public void heapSort(){
		int temp;
		int[] arry = {1,2,3,4,5,3,2,1,6,9};
		//使得树满足堆的特性，任何节点的所有子节点都小于该节点
		for(int i=arry.length/2-1;i>=0;--i){
			heapAdjust(arry,i,arry.length);
		}
		//将根节点与树的最后一个节点交互，树节点数量递减，同时在调整堆
		for(int i=arry.length-1;i>0;i--){
			temp = arry[i];
			arry[i]=arry[0];
			arry[0]=temp;
			heapAdjust(arry, 0,i);
		}
		System.out.println(Arrays.toString(arry));
	}
	/**
	 * 调整head的节点与直接子节点，使得最大的在head位置。当与子节点发生交互后同时使得交换后的节点与其子节点比较依次进行下去
	 * @param arry
	 * @param head
	 * @param size
	 */
	public static void heapAdjust(int[] arry,int head,int size){
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
	}
}
