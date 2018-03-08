package com.specialtroops.chapter01;

/**
 * Created by tend on 2018/3/7.
 */
public class StringBuilderTest {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("a").append("b").append("c22222");
        System.out.println(stringBuilder.capacity());
        System.out.println(stringBuilder.length());
    }
}
