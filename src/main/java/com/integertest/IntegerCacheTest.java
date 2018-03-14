package com.integertest;

/**
 * Created by tend on 2018/1/23.
 */
public class IntegerCacheTest {
    public static void main(String[] args) {
        Integer a = 4;
        Integer b = 4;
        Integer a1 = 129;
        Integer b1 = 129;
        System.out.println(a == b);
        System.out.println(a1 == b1);
        int i = 0;
        int d = (i++)+ (++i)+ (i++);
        System.out.println("d = [ " + d + " ]");
    }
}
