package com.ydcun.java.interview.meituan;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        String[] line1 = sc.nextLine().split(" ");
        int[] array = new int[n];
        for (int i=0;i<line1.length;++i) {
            array[i] = Integer.parseInt(line1[i]);
        }
        int k = Integer.parseInt(sc.nextLine());
        for (int i = n; i > 0; --i) {
            for (int j = 0; j <= array.length - i; ++j) {
                int sum = 0;
                for (int m = 0; (m < i) && ((j + m) < n); ++m) {
                    sum += array[m+j];
                }
                if (sum % k == 0) {
                    System.out.println(i);
                    return;
                }
            }
        }
        System.out.println(0);
    }
}