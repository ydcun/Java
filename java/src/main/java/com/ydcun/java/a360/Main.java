package com.ydcun.java.a360;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
		String str1 = null;//保存从n到m经过的旗子序列
		String str2 = null;//第一次醒来看到的序列
		String str3 = null;//第二次醒来看到的序列
		String temp1 = "forward";//正向
		String temp2 = "backward";//反向
		String temp3 = "both";//两种都有可能
		String temp4 = "invalid";//都不可能
		boolean isok1 = false;
		boolean isok2 = false;
		StringBuffer sb = new StringBuffer();
		while(true){
			str1 = strin.readLine();
			if(str1==null || str1.equals("")){
				break;
			}
			str2 = strin.readLine();
			str3 = strin.readLine();
			
			//计算匹配
			//正向查找
			isok1 = fowWard(str1,str2,str3);
			String temp=null;
			StringBuffer tempSb = new StringBuffer();
			for(int i=0;i<str1.length();i++){
				tempSb.append(str1.charAt(str1.length()-i-1));
			}
			//反向查找
			isok2 = fowWard(tempSb.toString(),str2,str3);
			
			if(isok1){
				if(isok2){
					sb.append(temp3+"\n");
				}else{
					sb.append(temp1+"\n");
				}
			}else{
				if(isok2){
					sb.append(temp2+"\n");
				}else{
					sb.append(temp4+"\n");
				}
			}
		}
		System.out.println(sb.toString());
	}
	private static boolean fowWard(String str1, String str2, String str3) {
		int index1 = str1.indexOf(str2);
		if(index1!=-1){
			String temp =str1.substring(index1+str2.length());
			int index2 = temp.indexOf(str3);
			if(index2!=-1){
				return true;
			}
		}
		return false;
	}

	
}
