package com.common.threadhandler;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/4/13 0:20
 * @since 1.0
 */
public class ThreadPoolHandler {
    static ArrayBlockingQueue<Runnable> runnables = new ArrayBlockingQueue<Runnable>(2);


    public static void main(String[] args) {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 5, 0, TimeUnit.HOURS,
                runnables,new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 9; i ++){
            executor.execute(new Task(i));
        }
        for (int i = 0; i < runnables.size(); i ++){
            System.out.println("size=" + runnables.size() + "task---name = " + ((Task)runnables.peek()).getName());
        }
    }

    static class Task implements Runnable{
        private int name;

        public int getName() {
            return name;
        }

        public void setName(int name) {
            this.name = name;
        }

        public Task() {
        }

        public Task(int name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true){

            }
        }
    }
}
