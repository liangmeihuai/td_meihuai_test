package com.deep.jvm.charpterseven;

/**
 * Created by tend on 2018/2/27.
 */
public class BootStrapClassLoderTest {
    public static void main(String[] args) {
        System.out.println(java.lang.ClassLoader.getSystemClassLoader());
        System.out.println(java.lang.String.class.getClassLoader());
    }
}
