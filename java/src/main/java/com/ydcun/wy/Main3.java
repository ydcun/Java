package com.ydcun.wy;

import java.math.BigInteger;
import java.util.Scanner;

public class Main3 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String s = sc.nextLine();
        String[] arry = s.split(" ");
        BigInteger n = new BigInteger(arry[0]);
        BigInteger k = new BigInteger(arry[1]);

        BigInteger a = k.pow(n.intValue());



       System.out.print("3");

    }
}