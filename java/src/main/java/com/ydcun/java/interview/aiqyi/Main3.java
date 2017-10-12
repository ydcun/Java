package com.ydcun.java.interview.aiqyi;

import java.util.Scanner;

public class Main3 {
    public static void main(String[] arge){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        Integer count = 0;
        for(int i=1;i<=n;i++){

            for(int j=1;j<=m;j++){
                if(isOk(i,j)){
                    count++;
                }
            }
        }
        System.out.print(count);

    }
    public static boolean isOk(int a,int b ){
        //double tab = Math.sqrt(a*b);
        //if(tab%1==0)
        //    return true;
        //if(a>b){
        //    if(Math.sqrt(a)%Math.sqrt(b)==0)
        //        return true;
        //}else{
        //    if(Math.sqrt(b)%Math.sqrt(a)==0)
        //        return true;
        //}
        if((Math.sqrt(a)+Math.sqrt(b))%1==0)
            return true;
        return  false;
    }
}
