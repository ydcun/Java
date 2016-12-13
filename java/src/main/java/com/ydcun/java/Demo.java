/**
 * 
 */
package com.ydcun.java;

/**
 * @author ydcun-psjs
 *
 */
public class Demo {
public static void main(String[] args) {
	Integer  a1 = new Integer(100);
	Integer a2= new Integer(100);
	Integer b1 =1000;
	Integer b2=1000;
	System.out.println(a1==a2);
	System.out.println(b1==b2);
	System.out.println(a1.equals(a2));
	System.out.println(b1.equals(b2));
}
}
