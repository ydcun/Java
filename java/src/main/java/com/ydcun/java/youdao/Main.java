package com.ydcun.java.youdao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static void main(String args[]) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		for (int i = 0; i < num; i++) {
			List<Integer> list1 = new ArrayList<>();
			List<Integer> list2 = new ArrayList<>();
			List<Integer> list = new ArrayList<>();
			int n = scan.nextInt();
			int k = scan.nextInt();
			for (int j = 0; j < n; j++) {
				list1.add(scan.nextInt());
			}
			for (int j = 0; j < n; j++) {
				list2.add(scan.nextInt());
			}
			for (int j = 0; j < k; j++) {
				list.clear();
				shuffle(list1, list2, list);
				list1.clear();
				list2.clear();
				for (int l = 0; l < 2 * n; l++) {
					if (l <= n - 1) {
						list1.add(list.get(l));
					} else {
						list2.add(list.get(l));
					}
				}
			}
			for (int j = 0; j < 2 * n - 1; j++) {
				System.out.print(list.get(j) + " ");
			}
			System.out.println(list.get(2 * n - 1));
			list1.clear();
			list2.clear();
		}
		scan.close();
	}

	public static void shuffle(List<Integer> list1, List<Integer> list2, List<Integer> list) {
		int size = list1.size();
		for (int i = size - 1; i >= 0; i--) {
			list.add(0, list2.get(i));
			list.add(0, list1.get(i));
		}
	}
}