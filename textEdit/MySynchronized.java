import java.util.*;
public class MySynchronized{
	public static void main(String[] args){
		final Output output = new Output();
		new Thread(new Runnable(){
			public void run(){
			while(true){
					try{
						Thread.sleep(10);
					}catch(Exception e){
						e.printStackTrace();
					}
			
				output.method1();
				}
			}
		}).start();
		while(true){
			try{
				Thread.sleep(10);
			}catch(Exception e){
				e.printStackTrace();
			}
			output.method3();
		}
	}

}
class Output{
	public static void myPrint(String name){
		for(int i=0;i<name.length();i++){
			System.out.print(name.charAt(i));
		}
		System.out.println();
	}
	//this
	public synchronized void method1(){
		String name = "yudiancun";
		myPrint(name);
	}
	//this
	public void method2(){
		synchronized(this){
			String name ="duanqiongqiong";
			myPrint(name);
		}
	}
	//Output.class
	public static synchronized void method3(){
		String name = "wangxiaorui";
		myPrint(name);
	}
}
