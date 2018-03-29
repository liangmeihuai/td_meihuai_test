package com.forkjoin;

import java.util.concurrent.*;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/29 20:47
 * @since 1.0
 */
public class ForkJoinPoolReturnTest {
    private static final int THREAD_SHOLD = 20;
    private static final int COUNT = 100;
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        int[] arr = new int[COUNT];
        int sum = 0;
        for (int i = 0; i < COUNT; i++){
            arr[i] = i;
            sum += arr[i];
        }
        System.out.println("before forkjoin sum is [ " + sum + " ]");
        Future<Integer> future = forkJoinPool.submit(new ForkJoinTask(arr,0,arr.length));
        System.out.println("compute by forkjoinPool sum is [ " + future.get() + "]");
        forkJoinPool.shutdown();
    }
    static class ForkJoinTask extends RecursiveTask<Integer>{
        private int[] arr;
        private int start;
        private int end;

        public ForkJoinTask(int[] arr, int start, int end) {
            this.arr = arr;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Integer compute() {
            int sum = 0;
            if (end - start < THREAD_SHOLD){
                try {
                    // 为了让这个线程睡眠，不至于它执行过快，又去拉取新的任务
                    TimeUnit.MILLISECONDS.sleep(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int i = start; i < end; i++){
                    sum += arr[i];
                }
                System.out.println(Thread.currentThread().getName() + " count is [ " + sum + " ]");
                return sum;
            }else {
                int middle = (start + end) / 2;
                ForkJoinTask left = new ForkJoinTask(arr, start, middle);
                ForkJoinTask right = new ForkJoinTask(arr, middle, end);
                left.fork();
                right.fork();
                return left.join() + right.join();
            }
        }
    }
}
