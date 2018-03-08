package com.specialtroops.chapter01;

/**
 * Created by tend on 2018/3/7.
 */

import org.apache.commons.lang.exception.ExceptionUtils;

/**
 * getChars() 方法将字符从字符串复制到目标字符数组。

 语法
 public void getChars(int srcBegin, int srcEnd, char[] dst,  int dstBegin)
 参数
 srcBegin -- 字符串中要复制的第一个字符的索引。

 srcEnd -- 字符串中要复制的最后一个字符之后的索引。

 dst -- 目标数组。

 dstBegin -- 目标数组中的起始偏移量。

 返回值
 没有返回值，但会抛出 IndexOutOfBoundsException 异常。
 */
public class GetCharsTest {
//    public static void main(String args[]) {
//        String Str1 = new String("www.runoob.com");
//        char[] Str2 = new char[6];
//
//        try {
////            Str1.getChars(4, 10, Str2, 0);
//            Str1.getChars(4, 10, Str2, 2); //会触发异常
//            Str1.getChars(4, 8, Str2, 2);
//            System.out.print("拷贝的字符串为：" );
//            System.out.println(Str2 );
//        } catch( Exception ex) {
//            System.out.println("触发异常..." + ExceptionUtils.getStackTrace(ex));
//        }
//    }
public static void main(String[] args) {
    Person p1 = new Person(1, "luke");
    Person p2 = new Person(1, "luke");
    System.out.println(p1.equals(p2));
}
}
