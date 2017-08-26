package com.ydcun.didi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] array = in.nextLine().split(" ");
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<array.length;i++){
            list.add(Integer.parseInt(array[i]));
        }
        int k = Integer.parseInt(in.nextLine());
        //第一题
       // int max = maxSubArray(list);
        // System.out.println(max);

        //第二题
        int kmax = findKthLargest(list,k);
        System.out.println(kmax);

    }
    public static int findKthLargest(List<Integer> nums, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>(k);
        for (int i : nums) {
            q.offer(i);

            if (q.size() > k) {
                q.poll();
            }
        }

        return q.peek();
    }
    //连续最大和
    public static int maxSubArray(List<Integer> list) {
        int newsum=list.get(0);
        int max=list.get(0);
        for(int i=1;i<list.size();i++){
            newsum=Math.max(newsum+list.get(i),list.get(i));
            max= Math.max(max, newsum);
        }
        return max;
    }
}
