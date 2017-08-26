package com.ydcun.wy.jrtt.one;

import java.util.*;
class order {
    int x;
    int y;
    boolean ok;
}
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) {
            int n = in.nextInt();
            ArrayList<order> li = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                order or = new order();
                or.x = in.nextInt();
                or.y = in.nextInt();
                or.ok = false;
                li.add(or);
            }

            Collections.sort(li, new Comparator<order>() {

                public int compare(order o1, order o2) {

                    Integer x1 = o1.x;
                    Integer x2 = o2.x;
                    return x1.compareTo(x2);
                }
            });

            int y = li.get(n - 1).y;
            for (int i = n - 1; i >= 0; i--) {
                order ol = li.get(i);
                ol.ok = false;
                if (y <= li.get(i).y) {
                    ol.ok = true;
                    y = ol.y;
                }
            }

            for (int i = 0; i < n; i++) {
                order ol = li.get(i);
                if (ol.ok) {
                    System.out.println(ol.x + " " + ol.y);
                }
            }
        }
    }
}
