package com.distributejava.chapterfive;

/**
 * Created by tend on 2018/1/26.
 * Thread.sleep(1000)不会释放锁，也不会释放cpu资源
 * new Object().wait()释放锁，也释放cpu资源
 */
public class WaitReleaseTest {
    private static final Object obj = new Object();
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " start!");
        new AThread().start();
        Thread.sleep(1000);
        synchronized (obj){
            System.out.println(Thread.currentThread().getName() + " i can do this");
//            obj.notify();
        }
    }

    static class AThread extends Thread{
        @Override
        public void run() {
            super.run();
            try {
                synchr();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "A thread ......End");
        }
    }

    private static void synchr() throws InterruptedException {
        synchronized (obj){
            System.out.println(Thread.currentThread().getName() + " before wait");
//            obj.wait(5000);
//            Thread.sleep(5000);
//            obj.wait();
            System.out.println(Thread.currentThread().getName() + " after wait");
        }
    }
}
