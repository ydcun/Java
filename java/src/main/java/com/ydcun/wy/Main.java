package com.ydcun.wy;

import com.sun.tools.hat.internal.parser.ReadBuffer;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
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