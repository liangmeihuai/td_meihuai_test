package com.common.readwritelock.condition;

import java.util.concurrent.locks.Condition;
 import java.util.concurrent.locks.Lock;
 import java.util.concurrent.locks.ReentrantLock;

 public class SignalTest2 {
     public static void main(String[] args) {
         new SignalTest2().init();
     }

     private void init() {
         final Business b = new Business();
         new Thread() {
             public void run() {
                 for (int i = 0; i < 5; i++)
                     b.sub1();
             }
         }.start();

         new Thread() {
             public void run() {
                 for (int i = 0; i < 5; i++)
                     b.sub2();
             }
         }.start();

         new Thread() {
             public void run() {
                 for (int i = 0; i < 5; i++)
                     b.sub3();
             }
         }.start();
     }

     private class Business {
         int status = 1;//开始默认执行第一个方法
         Lock lock = new ReentrantLock();
         Condition cond1 = lock.newCondition();
         Condition cond2 = lock.newCondition();
         Condition cond3 = lock.newCondition();

         public void sub1() {
             lock.lock();
             while (status != 1) {
                 try {
                     cond1.await();
                 } catch (Exception e) {
                 }
             }
             for (int i = 1; i <= 5; i++) {
                 try {
                     Thread.sleep(200);
                 } catch (Exception e) {}
                 System.out.println("[sub1]" + Thread.currentThread().getName() + ":" + i);
             }
             status = 2;//1执行完指定2开始执行
             cond2.signal();
             lock.unlock();
         }

         public void sub2() {
             lock.lock();
             while (status != 2) {
                 try {
                     cond2.await();
                 } catch (Exception e) {
                 }
             }
             for (int i = 1; i <= 5; i++) {
                 try {
                     Thread.sleep(200);
                 } catch (Exception e) {}
                 System.out.println("[sub2]" + Thread.currentThread().getName() + ":" + i);
             }
             status = 3;//2执行完指定3开始执行
             cond3.signal();
             lock.unlock();
         }

         public void sub3() {
             lock.lock();
             while (status != 3) {
                 try {
                     cond3.await();
                 } catch (Exception e) {
                 }
             }
             for (int i = 1; i <= 5; i++) {
                 try {
                     Thread.sleep(200);
                 } catch (Exception e) {
                 }
                 System.out.println("[sub3]" + Thread.currentThread().getName() + ":" + i);
             }
             status = 1;//3执行完指定1开始执行
             cond1.signal();
             lock.unlock();
         }
     }
 }