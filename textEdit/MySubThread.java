import java.util.*;
public class MySubThread{
	public static void main(String[] args){
		final Business business = new Business();
		new Thread(new Runnable(){
			public void run(){
				for(int i=0;i<50;i++){
					business.sub(i);
				}
			}
		}).start();
		for(int i=0;i<50;i++){
			business.main(i);
		}
	}
}
class Business{
	private boolean sub = true;
	public synchronized void sub(int j){
		while(!sub){
			try{
			this.wait();
			}catch(Exception e){e.printStackTrace();}
		}
		for(int i=0;i<10;i++){
			System.out.println("sub thread sequece of"+i+" loop of"+j);
		}
		sub=false;
		this.notify();

	}
	public synchronized void main(int j){
		while(sub){
			try{
			this.wait();
			}catch(Exception e){e.printStackTrace();}
		}
		for(int i=0;i<100;i++){
			System.out.println("main thread sequece of "+i+" loop of "+j);
		}
		sub=true;
		this.notify();
	}
}
