/**
 * 
 */
package com.ydcun.tianchi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ydcun-psjs
 *  start 2015-07-01
 *  en 2016-10-31
 */
public class Main {
	static SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");

	//开始结束时间
	static Date start_date = null;
	static Calendar start_cal = null;
	static Date end_date = null;
	static Calendar end_cal= null;
	static{
		try {
			start_date = sf.parse("2015-07-31");
			start_cal = Calendar.getInstance();
			start_cal.setTime(start_date);
			
			end_date = sf.parse("2016-10-31");
			end_cal = Calendar.getInstance();
			end_cal.setTime(end_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		String path = Main.class.getResource(".").getPath();
		System.out.println("目录："+path);
		File file = new File(path+"shop_pay_day2ucnt.csv");
		
		//按行读取文件
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String tempString;
		int index=1;
		
		//储存不通商店特征
		List<List<String>> list = new ArrayList<List<String>>();
		FileWriter fw = null;
		fw = new FileWriter(path.substring(1)+"feature.csv");
		while((tempString = reader.readLine())!=null){
			System.out.println(index+":"+tempString);
			
			StringBuffer sb = null;
			String[] array = tempString.split(",");
			List<String> temp = new ArrayList<String>();
			Calendar currDate = Calendar.getInstance();
			currDate.setTime(start_date);
			
			for(int i=0;i<array.length;i++){
				sb = new StringBuffer();
				//获取前七天数据
				sb.append(get7Day(currDate.getTime(),array));
				//获取前七个通星期数据
				sb.append(get7Week(currDate.getTime(),array));
				//获取前七个同号数据
				sb.append(get7Month(currDate.getTime(),array));
				temp.add(sb.toString());
				currDate.add(Calendar.DATE,1);
			}
			System.out.println(index+":"+temp.size());
			
			//写入文件
			for (int i = 0; i < temp.size(); i++) {
				fw.write(temp.get(i)+index+"\n\r");
			}
			//下一行
			index++;
		}
		fw.flush();
		fw.close();
	}
	//获取该日期前七天数据串
	public static String get7Day(Date date,String[] array) throws Exception{
		StringBuffer bf = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for(int i=0;i<7;i++){
			//System.out.println("s:"+sf.format(cal.getTime()));
			cal.add(Calendar.DATE, -1);//减一天
			//System.out.println("e:"+sf.format(cal.getTime()));
			if(cal.getTime().before(start_date)){
				bf.append("0,");
				continue;
			}
			int index = daysBetween(start_date, cal.getTime());
			bf.append(array[index]+",");
		}
		return bf.toString();
	}

	//获取该日期前七周同星期的数据串
	public static String get7Week(Date date,String[] array) throws Exception{
		StringBuffer bf = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for(int i=0;i<7;i++){
			//System.out.println("s:"+sf.format(cal.getTime()));
			cal.add(Calendar.WEEK_OF_YEAR, - 1);//减一周
			//System.out.println("e:"+sf.format(cal.getTime()));
			if(cal.getTime().before(start_date)){
				bf.append("0,");
				continue;
			}
			int index = daysBetween(start_date, cal.getTime());
			bf.append(array[index]+",");
		}
		return bf.toString();
	}
	//获取该日期前七月同号数据串
	private static String get7Month(Date date, String[] array) throws Exception {
		StringBuffer bf = new StringBuffer();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		for(int i=0;i<7;i++){
			//System.out.println("s:"+sf.format(cal.getTime()));
			cal.add(Calendar.MONTH, - 1);//减一个月
			//System.out.println("e:"+sf.format(cal.getTime()));
			if(cal.getTime().before(start_date)){
				bf.append("0,");
				continue;
			}
			int index = daysBetween(start_date, cal.getTime());
			bf.append(array[index]+",");
		}
		return bf.toString();
	}
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    } 
}
