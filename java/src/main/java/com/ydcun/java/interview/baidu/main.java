/**
 * 
 */
package com.ydcun.java.interview.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author ydcun-psjs
 *
 */
public class main {
	public static void main(String[] args) throws IOException {
		BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
		String str = strin.readLine();
		Map<Character,Integer> base = new HashMap<Character,Integer>();
		Character temp = str.charAt(0);
		int length=0;
		for(int i=0;i<=str.length();i++){
			Character tempChar = str.charAt(i);
			if(temp.equals(tempChar)){
				length++;
			}else{
				Integer sum = base.get(temp);
				if(sum==null){
					sum=0;
				}
				if(length>sum){
					sum = length;
				}
				base.put(temp, sum);
				length=0;
				temp = tempChar;
				i--;
			}
			
		}
		int s = 0;
		for(Character c:base.keySet()){
			s+=base.get(c);
		}
		System.out.println(s);
		
		
	}
}
