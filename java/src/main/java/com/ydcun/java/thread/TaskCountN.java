/**
 * 
 */
package com.ydcun.java.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author ydcun-psjs
 * 创建任务，重复执行当返回成功后结束
 */
class TimerTaskTest extends TimerTask{  
	private int count =0;
	private int maxcount =3;
    @Override  
    public void run() {  
    	System.out.println("线程开始："+count);
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	if(count>=maxcount-1){
    		System.gc();
            cancel();
    	}
        Date date = new Date(this.scheduledExecutionTime());  
        System.out.println(Thread.currentThread()+" : 本次执行该线程的时间为：" + date+" : "+count);
        count++;
    }  
}  
public class TaskCountN {
	
	
	public static void main(String[] args) {
		Timer timer = new Timer();  
        timer.schedule(new TimerTaskTest(), 1000, 2000);  
        timer.schedule(new TimerTaskTest(), 1000, 2000);  
		System.out.println("main end");
	}
}
