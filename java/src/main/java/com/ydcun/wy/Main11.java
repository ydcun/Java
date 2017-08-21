package com.ydcun.wy;

import java.math.BigInteger;
import java.util.Scanner;
public class Main11 {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int x;
        int p;
        int f;
        int d;
        x = sc.nextInt();
        f = sc.nextInt();
        d = sc.nextInt();
        p = sc.nextInt();
        if ((d / x) <= f) {
            System.out.println(d / x);
        } else {
            System.out.println(f + (d - x * f) / (x + p));
        }
    }
}