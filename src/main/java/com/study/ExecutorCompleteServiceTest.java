package com.study;

import java.util.concurrent.*;

/**
 * Created by tend on 2017/10/31.
 * ExecutorCompletionService 是将 Executor和BlockQueue结合的jdk类，其实现的主要目的是：提交任务线程，
 * 每一个线程任务直线完成后，将返回值放在阻塞队列中，然后可以通过阻塞队列的take()方法返回 对应线程的执行结果！！
 */
public class ExecutorCompleteServiceTest {
    public static void main(String[] args) throws InterruptedException, ExecutionException, ExecutionException {
        int num = 9;
        ExecutorCompletionService<String> executorCompletionService = new ExecutorCompletionService<>(MyThreadPool.getExecutor());
        for (int i = 0; i <= num; i++) {
            Thread.sleep(100l);
            executorCompletionService.submit(new Task(i));
        }
        for (int i = 0; i <= num; i++)
            System.out.println(executorCompletionService.take().get());
        MyThreadPool.getExecutor().shutdown();
    }


}

class MyThreadPool {

    private static class exe {
        private static ExecutorService executor = Executors.newCachedThreadPool();
    }

    private MyThreadPool() {
    }

    public static ExecutorService getExecutor() {
        return exe.executor;
    }
}

class Task implements Callable<String> {

    private volatile int i;

    Task(int i) {
        this.i = i;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000l);
        return Thread.currentThread().getName() + "任务 :" + i;
    }
}
