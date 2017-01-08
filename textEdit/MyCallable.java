import java.util.concurrent.*;
import java.util.*;
public class MyCallable{
	public static void main(String[] args) throws Exception{
		FutureTask<Long> ft = new FutureTask<Long>(new Callable<Long>(){
			public Long call() throws Exception{
				Thread.sleep(500);
				return new Date().getTime();
			}
		});
		new Thread(ft).start();
		System.out.print(ft.get());;
	}
}
