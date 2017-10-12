package com.ydcun.java.interview.Main;

import java.util.*;

import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc =new Scanner(System.in);
        int n= Integer.parseInt(sc.nextLine());
        String[] array = sc.nextLine().split(" ");

        int res =0,count=0;
        Set<Integer> set =new HashSet();
        set.add(res);

        for (int i=0;i<n;i++){
            res=res^Integer.parseInt(array[i]);
            if(set.contains(res)){
                count++;
                set.clear();
            }
            set.add(res);
        }
        System.out.print(count);
    }
}