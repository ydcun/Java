package com.ydcun.java.interview.tengxun;

import java.text.DecimalFormat;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Point {
    private int id;// 点的id
    private boolean flag = false;// 标志是否被遍历
    int sum;// 记录总的点个数

    private TreeMap<Integer, Integer> thisPointMap = new TreeMap<Integer, Integer>();// 该点到各点的距离。
    BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));

    Point(int sum) { // 构造函数 带有顶点个数
        this.sum = sum;
    }

    public void setId(int id) {// 设置顶点id
        this.id = id;
    }

    public int getId() {// 获得顶点id
        return this.id;
    }

    public void changeFlag() {// 修改访问状态。
        this.flag = true;
    }

    public boolean isVisit() {// 查看访问状态
        return flag;
    }

    public void setLenToOther(String[] line)throws IOException{// 初始化改点到各顶点的距离。
        for (int i = 0; i < sum; i++) {
            int len = Integer.valueOf(line[i]);
            thisPointMap.put(i, len);
        }
    }

    // 该点到顶尖id的 距离。
    public int lenToPointId(int id) {
        return thisPointMap.get(id);
    }
}

public class Main {
    public static List<Integer> listV = new ArrayList<Integer>();
    public static List<Integer> listP = new ArrayList<Integer>();
    public static void main(String[] args)throws IOException {
        ArrayList<Point> point_arr = new ArrayList<Point>();// 存储点集合
        BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0,k=0;
        String[] line1 = bufr.readLine().split(" ");
        sum = Integer.valueOf(line1[0]);
        k = Integer.valueOf(line1[1]);
        for(int i=0;i<sum;i++){
            String[] line = bufr.readLine().split("");
            Point p = new Point(sum);
            p.setId(i);
            p.setLenToOther(line);
            point_arr.add(p);
        }
        showDijkstra(point_arr, 0);

        int index = 0;
        for(index=0;index<listP.size();index++){
            if(listP.get(index)==1)
                break;
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int i=0;i<index;i++){
            list.add(listV.get(i));
        }

        double count=0;
        if(k>=list.size()){
            for(int i=0;i<list.size();i++){
                count+=list.get(i);
            }
            count/=2.0;
        }else{
            Collections.sort(list);
            for(int i=0;i<list.size();i++){
                if(i<=k){
                    count+=list.get(i)/2.0;
                }else{
                    count+=list.get(i);
                }
            }
        }
        DecimalFormat df   = new DecimalFormat("######0.0");
        System.out.println(df.format(count));
    }
    public static void showDijkstra(ArrayList<Point> arr, int i) {
        listP.add(i);
        arr.get(i).changeFlag();
        Point p1 = getTopointMin(arr, arr.get(i));
        if (p1 == null)
            return;
        int id = p1.getId();
        showDijkstra(arr, id);
    }
    public static Point getTopointMin(ArrayList<Point> arr, Point p) {
        Point temp = null;
        int minLen = Integer.MAX_VALUE;
        for (int i = 0; i < arr.size(); i++) {
            // 当已访问 或 者是自身或者无该路径时跳过。
            if (arr.get(i).isVisit() || arr.get(i).getId() == p.getId() || p.lenToPointId(i) < 0)
                continue;
            else {
                if (p.lenToPointId(i) < minLen) {
                    minLen = p.lenToPointId(i);
                    temp = arr.get(i);
                }
            }
        }
        if (temp == null)
            return temp;
        else {
            listV.add(minLen);
        }
        return temp;
    }
}
