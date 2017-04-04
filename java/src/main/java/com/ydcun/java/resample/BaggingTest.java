package com.ydcun.java.resample;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;


/*
 * 参考链接
 * http://blog.csdn.net/luckymelina/article/details/20920773
 * 
 * */
public class BaggingTest {
	/**
	   * if data has N cases, sample N cases at random without replacement.
	   * 
	   * @author melina
	   * 
	   * @param N
	   *          numbers of cases
	   * 
	   * @return N次取值并去重之后剩余的数的个数
	   */
	public static int runBagging(int N){
		Random rng = new Random();
	    ArrayList<Integer> list = new ArrayList<Integer>(); 
	    
	    for (int i = 0; i < N; i++) {
	      int index = rng.nextInt(N);
	      list.add(index);
	    }
	    //合并相同的取值
	    HashMap<String, Integer> hash = new HashMap<String, Integer>();  
        for (int i = 0; i < list.size(); i++) {  
            try {  
  
                if (!hash.isEmpty() && hash.containsKey(list.get(i))) {  
                    hash.put(list.get(i).toString(), hash.get(list.get(i)) + 1);  
                } else {  
                    hash.put(list.get(i).toString(), 1);  
                }  
            } catch (Exception e) {  
  
            }  
        }  
        /*Set<String> set = hash.keySet();  
        for (String key : set) {  
            System.out.println(key + "==>" + hash.get(key));  
        }  */
		return hash.keySet().size();
	}
	
	public static void main(String []args){
		int itr_num = 10000;   //迭代次数
		int datasize = 100;    //bagging的样本数目，此处为0~99之间100个数字做bagging
		ArrayList<Integer> list = new ArrayList<Integer>();		
		for(int i = 0; i < itr_num; i ++){
			int num = runBagging(datasize);
			list.add(num);
		    //System.out.println("第"+i+"次bagging去重之后的个数："+ num);
		    //System.out.println(num);
		}
		
		//统计 相同的数目在全部迭代后出现的频率
		HashMap<String, Integer> hash = new HashMap<String, Integer>();  
        for (int i = 0; i < list.size(); i++) {  
            try {  
  
                if ((!hash.isEmpty() )&&( hash.containsKey(list.get(i).toString()))) {  
                    hash.put(list.get(i).toString(), Integer.valueOf(hash.get(list.get(i).toString())) + 1);  
                } else {  
                    hash.put(list.get(i).toString(), 1);  
                }  
            } catch (Exception e) {  
  
            }  
        }
        Set<String> set = hash.keySet();  
        for (String key : set) {  
        	double itr_double=itr_num*1.0;  
        	double value =  hash.get(key)/itr_double;
        	DecimalFormat df = new DecimalFormat("0.00%");
        	
            System.out.println(key + " " + df.format(value));  
        }
        
	}

}
