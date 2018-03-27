package com.read.study.threadtool;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/27 15:02
 * @since 1.0
 */
public class CountDownLatchTest {
    private static final int COUNT = 5;
    public static void main(String[] args) throws InterruptedException {
        final CountDownLatch countDownLatch =  new CountDownLatch(COUNT);
        for (int i = 0; i < COUNT; i ++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("我是第i个线程" + Thread.currentThread().getName());
                    countDownLatch.countDown();
                }
            }).start();
        }
        countDownLatch.await();
        System.out.println("Main End");
    }
}
