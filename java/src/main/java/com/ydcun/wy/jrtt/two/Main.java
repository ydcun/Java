package com.ydcun.wy.jrtt.two;

/**
 * Created by ydcun-pro on 2017/8/22.
 */

/**
 *


 #include<cstdio>
 #include<cstring>
 #include<string>
 #include<vector>
 #include<algorithm>
 #include <set>
 #include <cmath>
 #include <map>
 #include <list>
 #include <queue>
 #include <stack>
 #include <cstdlib>
 #include <ctime>
 #include <iostream>
 using namespace std;

 const int INF = 0x7fffffff;

 int num[555555];
 int ans;

 void dfs(int l, int r)
 {
 if(l > r) return ;
 int minn = 111, pos = l;
 int sum = 0;
 for(int i = l; i <= r; i++)
 {
 if(num[i] < minn)
 {
 minn = num[i];
 pos = i;
 }
 sum += num[i];
 }
 ans = max(ans, sum*minn);
 dfs(l, pos-1);
 dfs(pos+1, r);
 }

 int main()
 {
 int n;
 scanf("%d", &n);
 for(int i = 0; i < n; i++)
 scanf("%d", &num[i]);
 ans = 0;
 dfs(0, n-1);
 printf("%d\n", ans);
 return 0;
 }


 *
 * */



import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    static int ans;
    static List<Integer> array = null;
    public static void main(String[] arge){
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String line = sc.nextLine();
        array = new ArrayList<Integer>();
        for(String temp:line.split(" ")){
            array.add(Integer.parseInt(temp));
        }
        ans=0;
        dfs(0,n-1);
        System.out.print(ans);
    }
    public static void dfs(int l,int r){
        if(l > r) return ;
        int minn = 111, pos = l;
        int sum = 0;
        for(int i = l; i <= r; i++)
        {
            if(array.get(i) < minn)
            {
                minn = array.get(i);
                pos = i;
            }
            sum += array.get(i);
        }
        ans = ans>sum*minn?ans:sum*minn;
        dfs(l, pos-1);
        dfs(pos+1, r);
    }
}
