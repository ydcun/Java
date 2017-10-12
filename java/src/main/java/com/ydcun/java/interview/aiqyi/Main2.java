package com.ydcun.java.interview.aiqyi;

import java.util.*;

import java.util.Scanner;
public class Main2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        int max =0;
        if(str.equals("")) {
            max = 0;
        }else {
            Stack<Character> s = new Stack<Character>();
            max = 0;
            int index = 0;
            while (index < str.length()) {
                if (str.charAt(index) == '(') {
                    s.push(str.charAt(index));
                    index++;
                } else if (str.charAt(index) == ')') {
                    max = Math.max(max, s.size());
                    s.pop();
                    index++;
                }
            }
        }
        System.out.println(max);

    }
}