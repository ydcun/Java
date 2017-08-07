/**
 * 
 */
package com.ydcun.java.leetcode;

/**
 * @author ydcun-psjs 原题连接：https://leetcode.com/problems/rectangle-area/
 *         求两个矩形面积和（去除重叠面积）
 */
public class RectangleArea223 {
	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
		double area = 0;
		double left = A > E ? A : E;
		double right = C < G ? C : G;
		double top = D < H ? D : H;
		double bottom = B > F ? B : F;
		double length = (right - left) > 0 ? (right - left) : 0;
		double height = top - bottom > 0 ? (top - bottom) : 0;

		area = length * height;
		return (int)((double)(C - A) * (D - B) + (double)(G - E) * (H - F) - area);
    }

	public static void main(String[] args) {
		// double area = new RectangleArea223().computeArea(-3, 0, 3, 4, 0, -1, 9,
		// 2);
//		 int area = new RectangleArea223().computeArea(-2, -2, 2, 2, -2, -2, 2, 2);
//		int area = new RectangleArea223().computeArea(-1500000001, 0, -1500000000, 1, 1500000000, 0, 1500000001, 1);
		int area = new RectangleArea223().computeArea(0,0,50000,40000,0,0,50000,40000);
		System.out.println("area=" + area);
	}
}
