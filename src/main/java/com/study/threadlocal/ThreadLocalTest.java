package com.study.threadlocal;

/**
 * Created by tend on 2018/1/18.
 */
public class ThreadLocalTest {
    public static void main(String[] args) throws InterruptedException {
        final ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
            @Override
            protected String initialValue() {
                return "initValue";
            }
        };
        System.out.println(threadLocal.hashCode());
        String str = "aaa";
        System.out.println(str.toString());
        System.out.println(Thread.currentThread().getName() + "--1=" + threadLocal.get());
        new Thread(new MyThread(threadLocal)).start();
        Thread.sleep(2000);
        System.out.println(Thread.currentThread().getName() + "--2=" + threadLocal.get());
    }

    static class MyThread implements Runnable{
        private ThreadLocal<String> threadLocal;

        public MyThread(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            threadLocal.set("inner thread value");
            System.out.println(Thread.currentThread().getName() + "--inner=" + threadLocal.get());
            threadLocal = null;
        }
    }
}
