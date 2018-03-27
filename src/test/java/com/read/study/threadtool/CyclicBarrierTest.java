package com.read.study.threadtool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/27 15:10
 * @since 1.0
 */
public class CyclicBarrierTest {
    private static final int COUNT = 5;
    public static void main(String[] args) {
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(COUNT, new Runnable() {
            @Override
            public void run() {
                System.out.println("我们准备下一个环节");
            }
        });

        for (int i = 0; i < COUNT; i++){
            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        System.out.println("走完第一环节");
                        cyclicBarrier.await();
                        System.out.println("第二环节.............");
                        cyclicBarrier.await();
                        System.out.println("第三环节........ll");
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }
}
