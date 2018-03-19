package com.common.xy;

/**
 * Created by tend on 2018/3/16.
 */
public class XYTest {
    public static void main(String[] args) {
        int x = 1;
        int y = 2;
        x *= y+8;
        System.out.println("x = [ " + x + " ]");
        System.out.println("y = [ " + y + " ]");
        System.out.println(F(3 + 5));
    }
    static int F(int x)
    {return (x*x);}
}
