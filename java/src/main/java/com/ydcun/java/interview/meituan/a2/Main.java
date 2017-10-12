package com.ydcun.java.interview.meituan.a2;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String[] line1 = sc.nextLine().split(" ");
        int[] array = new int[n];
        int sum = 0;
        for (int i=0;i<line1.length;++i) {
            array[i] = Integer.parseInt(line1[i]);
            sum+=array[i];
        }

        for(int temp:array){
            if(temp>sum/2){
                System.out.print("No");
                return;
            }
        }
        System.out.print("Yes");
    }
}