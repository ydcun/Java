/**
 * 
 */
package com.ydcun;

/**
 * @author ydcun-psjs
 *
 */
interface DT {
	public void print();
	
}
class DT1 implements DT{
	public void print(){
		System.out.println("DT1");
	}
}
class DT2 implements DT{
	public void print(){
		System.out.println("DT2");
	}
}
public class Test{
//	public void ht(DT dt){
//		dt.print();
//	}
	public void ht(DT1 dt){
		dt.print();
	}
	public void ht(DT2 dt){
		dt.print();
	}
	public static void main(String[] args) {
		Test test = new Test();
		test.ht(new DT2());
		test.ht(new DT1());
		
	}
	
	
}


