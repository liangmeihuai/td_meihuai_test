package com.deep.jvm.chapterthree;

import org.junit.Test;

/**
 * Created by tend on 2018/2/2.
 */
public class AllocationCollectorTest {
    private static final int _1MB = 1024 * 1024;

    /**
     * VM参数：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    @Test
        public  void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;
        allocation1 = new byte[2 * _1MB];
        allocation2 = new byte[2 * _1MB];
        allocation4 = new byte[4 * _1MB];  // 出现一次Minor GC
        allocation3 = new byte[2 * _1MB];
    }


}
