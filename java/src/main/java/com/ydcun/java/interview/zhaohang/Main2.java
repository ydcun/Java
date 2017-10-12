package com.ydcun.java.interview.zhaohang;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main2 {

    public static int changeID(int id) {
        if (id == 1)
            return 2;
        else
            return 1;
    }

    public static boolean canWin(List<Integer> arr, int begin, int m, int sum1, int sum2, int id) {
        if (m - begin <= 2) {
            if (m - begin == 2) {
                if (id == 1)
                    sum1 += arr.get(m-2) + arr.get(m-1);
                else
                    sum2 += arr.get(m-2) + arr.get(m-1);
            }

            else if (m - begin == 1) {
                if (id ==1)
                    sum1 += arr.get(m-1);
                else
                    sum2 += arr.get(m-1);
            }

            if (sum1 > sum2) {
                if (id == 1)
                    return true;
                else
                    return false;
            }
            else {
                if (id == 1)
                    return false;
                else
                    return true;
            }
        }

        int sum11 = sum1;
        int sum22 = sum2;
        if (id == 1) {
            sum1 += arr.get(begin);
            sum11 += arr.get(begin) + arr.get(begin+1);
        }
        else {
            sum2 += arr.get(begin);
            sum22 += arr.get(begin) + arr.get(begin+1);
        }

        if (!canWin(arr, begin+1, m, sum1, sum2, changeID(id)) || !canWin(arr, begin+2, m, sum11, sum22, changeID(id))) {
            return true;
        }
        return false;
    }



    public static void main(String[] arge){
        Scanner sc = new Scanner(System.in);
        int m = Integer.parseInt(sc.nextLine());
        String[] array = sc.nextLine().split(" ");
        List<Integer> list = new ArrayList<Integer>();
        for(String t:array){
            list.add(Integer.parseInt(t));
        }
        if (m <= 2) {
            System.out.println("true");

        }else {
            if (!canWin(list, 1, m, list.get(0), 0, 2) || !canWin(list, 2, m, list.get(0) + list.get(1), 0, 2))
                System.out.println("true");
            else
                System.out.println("false");
        }
    }
}
