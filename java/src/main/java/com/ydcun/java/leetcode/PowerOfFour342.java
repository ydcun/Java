/**
 * 
 */
package com.ydcun.java.leetcode;

/**
 * @author ydcun-psjs
 * 原题连接：https://leetcode.com/problems/power-of-four/
 * num是4的幂
 * 
 */
public class PowerOfFour342 {
    public boolean isPowerOfFour(int num) {
        if(num <= 0)
            return false;
        if((num&(num-1))>0)
            return false;
        return (num&0x55555555)>0; 
    }
    public static void main(String[] args) {
    	PowerOfFour342 pof = new PowerOfFour342();
		for(int i=0;i<100;i++){
			if(pof.isPowerOfFour(i)){
				System.out.println("ok"+i);
			}else{
				System.out.println("false"+i);
			}
		}
		
	}
}
