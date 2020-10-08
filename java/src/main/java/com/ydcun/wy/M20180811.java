package com.ydcun.wy;

import java.util.Scanner;

public class M20180811 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] arrStr = scanner.nextLine().split(" ");
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(arrStr[i]);
        }

        int qCount = Integer.parseInt(scanner.nextLine());
        String[] qarrStr = scanner.nextLine().split(" ");
        for(int i=0; i<qCount;i++){
            int temp = Integer.parseInt(qarrStr[i]);
            v(temp, arr);
        }

    }
    public static void v(int temp, int[] arr){
        int[] tempArr = new int[arr.length];
        int count =0;
        for(int i=0;i<arr.length;i++){
            tempArr[i] = arr[i];
            count+=arr[i];
        }
        if(temp>count){
            return;
        }
        for(int i=0;true;i++){
            i=i%tempArr.length;
            if(tempArr[i]>0){
                tempArr[i]--;
                temp--;
                if(temp==0){
                    System.out.println(i+1);
                    return;
                }
            }

        }
    }
}
