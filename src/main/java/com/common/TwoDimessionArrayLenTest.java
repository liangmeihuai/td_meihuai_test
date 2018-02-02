package com.common;

/**
 * Created by tend on 2018/2/2.
 */
public class TwoDimessionArrayLenTest {
    // 二维数组的length是以第一维的长度为主
    public static void main(String[] args) {
        int[][] a1 = new int[1][2];
        int[][] a2 = new int[2][1];
        int[][] a3 = new int[3][4];
        int[][] a4 = new int[4][8];
        System.out.println("a1.length = " + a1.length);
        System.out.println("a2.length = " + a2.length);
        System.out.println("a3.length = " + a3.length);
        System.out.println("a4.length = " + a4.length);
    }
}
