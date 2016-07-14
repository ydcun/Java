package com.ydcun.java;

/**
 * Hello world!
 *
 */
class Base{
	int i=2;
	public Base(){
		this.display();
	}
	public void display() {
		System.out.println(this.i);
	}
	
}
class Derived extends Base{
	int i=22;
	public Derived(){
	}
	public void display(){
		System.out.println(i);
	}
}
public class App extends Derived
{
	int i=222;
	public void accesMid(){
		System.out.println(super.i);
	}
    public static void main( String[] args )
    {
    	new Base();
    	App app = new App();
    	Derived derived= app;
    	Base base = derived;
    	System.out.println(app.i);
    	System.out.println(derived.i);
    	System.out.println(base.i);
//    	derived.accesMid();
    }
}
