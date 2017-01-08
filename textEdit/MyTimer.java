import java.util.*;
public class MyTimer{
	public static void main(String[] args){
		new Timer().schedule(new TimerTask(){
			public void run(){
				new Timer().schedule(new TimerTask(){
					public void run(){
						System.out.println("bombing");
					}
				},4000);
			
				System.out.println("bombing");
			}
		},10000,6000);
		while(true){
			try{
				Thread.sleep(1000);	
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(new Date().getSeconds());
		}
	}
}
