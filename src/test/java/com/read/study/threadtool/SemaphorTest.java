package com.read.study.threadtool;

import com.specialtroops.chapter04.socket.Commons;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/27 15:26
 * @since 1.0
 */
public class SemaphorTest {
    private static final int COUNT = 3;
    private static final int TOTAL_THREAD = 20;
    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(COUNT);
        for (int i = 0; i < TOTAL_THREAD; i++){
            new Thread(new Runnable() {
                boolean acquired = false;
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        acquired = true;
                        TimeUnit.SECONDS.sleep(2);
                        Commons.logInfo("我是线程:" + Thread.currentThread().getName() + "获得许可权");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        if (acquired){
                            semaphore.release();
                        }
                    }
                }
            }).start();
        }
    }
}
