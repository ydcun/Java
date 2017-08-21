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
public class RadixSort {
	@Test
	public void test(){
		int temp;
		int[] arry = {11,22,33,42,15,39,20,41,64,449};
		System.out.println("\n排序前");
		System.out.println(Arrays.toString(arry));
		radixSort(arry,3);
	}

	/**
	 * 基数排序
	 */
	private void radixSort(int[] arry,int d) {
		int k = 0;
        int m = 1; //控制键值排序依据在哪一位
        int[][]temp = new int[10][arry.length]; //数组的第一维表示可能的余数0-9
        int[]order = new int[10]; //数组order 用来表示相同余数是i的数的个数
        while(m <= d)
        {
        	//将相同余数的值放到temp第一维下标中
            for(int i = 0; i < arry.length; i++)
            {
                int lsd = ((arry[i] / (int)Math.pow(10, m)) % 10);
                temp[lsd][order[lsd]] = arry[i];
                order[lsd]++;
            }
            //将temp中的数据收集到arry中
            for(int i = 0; i < 10; i++)
            {
                if(order[i] != 0)
                    for(int j = 0; j < order[i]; j++)
                    {
                    	arry[k] = temp[i][j];
                        k++;
                    }
                order[i] = 0;
            }
            k = 0;
            System.out.println("第"+m+"趟 "+Arrays.toString(arry));
            m++;
        }
        System.out.println(Arrays.toString(arry));
	}
}
