package com.forkjoin;

import org.omg.CORBA.TIMEOUT;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/29 18:34
 * @since 1.0
 */
public class ForkJoinPoolTest {
    private static final int THREAD_SHOLD = 20;
    private static final int COUNT = 100;

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool forkJoinPool =  new ForkJoinPool();
        forkJoinPool.submit(new ForkRecusiveAction(0, COUNT));
        forkJoinPool.awaitTermination(10, TimeUnit.SECONDS);
        forkJoinPool.shutdown();
    }

    static class ForkRecusiveAction extends RecursiveAction {
        private int start;
        private int end;

        public ForkRecusiveAction(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        protected void compute() {
            if (end - start < THREAD_SHOLD) {
                for (int i = start; i < end; i++) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + " print i = " + i);
                }
            } else {
                int middle = (start + end) / 2;
                ForkRecusiveAction leftFork = new ForkRecusiveAction(start, middle);
                ForkRecusiveAction rightFork = new ForkRecusiveAction(middle, end);
                leftFork.fork();
                rightFork.fork();
            }
        }
    }


}
