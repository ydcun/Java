package com.ydcun.java.lambda;

import java.util.Arrays;

public class MethodTest {
    public static void main(String[] args) {
        String[] str = "apple Job ydcun My".split(" ");
        Arrays.sort(str, String::compareToIgnoreCase);
        System.out.print(Arrays.toString(str));
    }
}
