package com.common;
// 静态块，构造块，
// 构造函数 (k =101) 101:t1 :i=>1,n=>1
// print方法 (k=102) 102:i=>0,n=>0

public class Text {

    public static int k = 100;
    public static Text t1 = new Text("t1");
    public static Text t2 = new Text("t2");
    public static int i = print("i");
    public static int n = 99;
    public int j = print("j");

    static {
        System.out.println("静态块");
    }

    {
        System.out.println("构造块");
    }
    public Text(String str) {
        System.out.println("构造函数："+(++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++i;
        ++n;
    }

    public static int print(String str) {
        System.out.println("pint方法："+(++k) + ":" + str + "   i=" + i + "    n=" + n);
        ++n;
        return ++i;
    }

    public static void main(String args[]) {
    	System.out.println("main");
        Text t = new Text("init");
    }

    // print方法: 101:j  i=
}
