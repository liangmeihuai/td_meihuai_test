package com.listenalltimesocket;

/**
 * Created by tend on 2018/1/8.
 */
public class MessageLenTest {
    public static void main(String[] args) {
        String str = "the msg you send to me is [aaa]\n";
        String str1 = "the msg you send to me is [" + "aaa" + "]\n";
        System.out.println("len = " + str.length());
        System.out.println("len1 = " + str1.length());
    }
}
