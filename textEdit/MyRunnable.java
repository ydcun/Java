public class MyRunnable{
	public static void main(String[] args){
		Thread thread = new Thread(new Runnable(){
			public void run(){
				while(true){
					try{
						Thread.sleep(500);
					}catch(Exception e){
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName());
				}
			}
		});
		thread.start();
		
	}

}
