package com.ydcun.java.a360;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

class Node{
	String name=null;
}
public class Main {
	public static List<Node> list = new ArrayList<Node>();
	public static int in=0;
	public static void main(String[] args) throws Exception {
		BufferedReader strin=new BufferedReader(new InputStreamReader(System.in));
		String str = strin.readLine();
		String[] arr = str.split(" ");
		int count = new Integer(arr[0]);
		int count2 = new Integer(arr[1]);
		for(int i=0;i<count2;i++){
			list.add(new Node());
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<count;i++){
			str = strin.readLine();
			String[] tempArr = str.split(" ");
			switch (tempArr[0]) {
			case "new":
				String result = newMethod(new Integer(tempArr[1]));
				sb.append(result+"\n");
				break;
			case "del":
				delMethod(new Integer(tempArr[1]));
				break;
			case "def":
				defMethod();
				break;
			default:
				break;
			}
		}
		System.out.println(sb.toString());
	}
	/**
	 * 
	 */
	private static void defMethod() {
		int i;
		for(i=0;i<list.size();i++){
			if(list.get(i).name==null){
				break;
			}
		}
		
		for(int j=i;j<list.size();j++){
			if(list.get(j).name!=null){
				list.get(i).name=list.get(j).name;
				list.get(j).name=null;
				i++;
			}
		}
	}
	/**
	 * @param integer
	 */
	private static void delMethod(Integer name) {
		for(int i=0;i<list.size();i++){
			if(list.get(i).name!=null &&list.get(i).name.equals(name+"")){
				list.get(i).name=null;
			}
		}
	}
	private static String newMethod(Integer size) {
		int count =0;
		int index=0;
		boolean isok=true;
		for(int i=0;i<list.size();i++){
			if(list.get(i).name==null && isok){
				index =i;
				isok = false;
			}
			if(list.get(i).name==null){
				count++;
				if(count==size){
					in++;
					for(int j=0;j<size;j++){
						if(j+index<list.size()){
							list.get(j+index).name=in+"";
						}
					}
					return in+"";
				}
			}else{
				isok=true;
				count=0;
			}
		}
		return "NULL";
	}
}
