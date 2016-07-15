/**
 * 
 */
package com.ydcun.java.leetcode;

/**
 * @author ydcun-psjs
 *
 */
public class PowerOfFour {
    public boolean isPowerOfFour(int num) {
        if(num <= 0)
            return false;
        if((num&(num-1))>0)
            return false;
        return (num&0x55555555)>0; 
    }
    public static void main(String[] args) {
    	PowerOfFour pof = new PowerOfFour();
		for(int i=0;i<100;i++){
			if(pof.isPowerOfFour(i)){
				System.out.println("ok"+i);
			}else{
				System.out.println("false"+i);
			}
		}
		
	}
}
