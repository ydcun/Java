/**
 * 
 */
package com.ydcun.java.arithmetic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.ydcun.java.sort.MergeSort;

/**
 * @author ydcun-psjs
 *
 */
public class InverseNumber {
	private MergeSort mergeSort = new MergeSort();
	@SuppressWarnings("resource")
	@Test
	public void MergeSortTest() throws Exception{
		String fileName = InverseNumber.class.getResource("").getPath()+"Q8.txt";
		System.out.println(fileName);
		File file = new File(fileName);
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader(file));
		String tempString;
		int line=0,i=0;
		int[] array = new int[100000];
		while((tempString = reader.readLine())!=null){
			array[i++] = Integer.parseInt(tempString);
		}
		
		mergeSort.mergeSort(array);
		System.out.println(mergeSort.count);
	}
}
