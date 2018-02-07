package com.deep.jvm.chapterthree;

/**
 * Created by tend on 2018/2/6.
 */
public class VOneTenuringThresholdTest {
    // 8192 * 0.39 = 初始值(3270K)
    private static final int _1MB = 1024 * 1024;
    public static void main(String[] args) throws InterruptedException {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[_1MB / 4];  // 什么时候进入老年代决定于XX:MaxTenuringThreshold设置
        allocation2 = new byte[4 * _1MB];
        allocation3 = new byte[4 * _1MB];
//        allocation3 = null;
//        allocation3 = new byte[4 * _1MB];
//        allocation4 = new byte[2 * _1MB];
    }
}
