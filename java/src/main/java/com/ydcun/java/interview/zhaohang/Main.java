package com.ydcun.java.interview.zhaohang;

import java.util.Scanner;

class Main{

     public static int get(int a, int b) {
         for (int i = Math.max(a, b);; i++) {
             if (i % a == 0 && i % b == 0) {
                 return i;
             }
         }
     }
     public static void main(String[] args) {
         Scanner sc = new Scanner(System.in);
         int a = sc.nextInt();
         int b = sc.nextInt();
         int n = sc.nextInt();

         int test = get(a, b);
         int i=0;
         do {
             i++;
         }while(test*i<n);
         System.out.println(i);
     }
 }
