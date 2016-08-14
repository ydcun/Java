/**
 * 
 */
package com.ydcun;

/**
 * @author ydcun-psjs
 *
 */
public class Test{
	class abc{
		public void print(){}
	}
	public static void main(String[] args) {
		String s1 = "a"+"ab";
		String s2 = "aab";
		String s3="ab";
		String s4= "a"+"ab";
		String s5= "a"+s3;
		System.out.println(s1==s2);
		System.out.println(s1==s5);
		System.out.println(s1.equals(s5));
		System.out.println(new Object());
		
		int x=3;
		int[] array = new int[]{2,3,4,5,6,7,8};
		m1(x,array);
		System.out.println("\n----------");
		System.out.println("x="+x);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}
	public static void m1(int x,int[] array){
		System.out.println("x="+x);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		x=10;
		array[0]=100;
	}
	
	
}


