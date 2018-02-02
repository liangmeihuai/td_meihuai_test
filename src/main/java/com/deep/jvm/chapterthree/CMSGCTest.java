package com.deep.jvm.chapterthree;

/**
 * Created by tend on 2018/2/1.
 */
public class CMSGCTest {
    // -Xms8M -Xmx8M -XX:NewRatio=3 -XX:CMSInitiatingOccupancyFraction=90 -XX:+PrintGCTimeStamps -XX:+PrintGCDetails
    private static  final  int _1M = 1 * 1024 * 1024;
    public static void main(String[] args) throws InterruptedException {
        byte[] a = new byte[3 * _1M];
        byte[] b = new byte[2 * _1M + 512 * 1024];
//        byte[] c = new byte[1 * _1M];
//        byte[] e = new byte[1 * _1M];
        System.out.println("gc trigger");
        Thread.sleep(3000);
    }

    /**
     * 这个GC日志可以好好研究一下
     * 0.176: [GC [PSYoungGen: 1024K->493K(1536K)] 1024K->677K(7680K), 0.0016926 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     0.234: [GC [PSYoungGen: 1517K->504K(1536K)] 1701K->1030K(7680K), 0.0018116 secs] [Times: user=0.00 sys=0.00, real=0.00 secs]
     gc trigger
     Heap
     PSYoungGen      total 1536K, used 1361K [0x00000000ffe00000, 0x0000000100000000, 0x0000000100000000)
     eden space 1024K, 83% used [0x00000000ffe00000,0x00000000ffed6760,0x00000000fff00000)
     from space 512K, 98% used [0x00000000fff80000,0x00000000ffffe010,0x0000000100000000)
     to   space 512K, 0% used [0x00000000fff00000,0x00000000fff00000,0x00000000fff80000)
     ParOldGen       total 6144K, used 4622K [0x00000000ff800000, 0x00000000ffe00000, 0x00000000ffe00000)
     object space 6144K, 75% used [0x00000000ff800000,0x00000000ffc83950,0x00000000ffe00000)
     PSPermGen       total 21504K, used 2913K [0x00000000fa600000, 0x00000000fbb00000, 0x00000000ff800000)
     object space 21504K, 13% used [0x00000000fa600000,0x00000000fa8d8498,0x00000000fbb00000)
     */
}
