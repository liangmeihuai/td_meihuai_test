package com.common.readwritelock.condition;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
 import java.util.concurrent.Executors;
 import java.util.concurrent.locks.Condition;
 import java.util.concurrent.locks.Lock;
 import java.util.concurrent.locks.ReentrantLock;

 public class ConditionTest {

     public static void main(String[] args) {
         ExecutorService service = Executors.newSingleThreadExecutor();
         final Business2 business = new Business2();
         service.execute(new Runnable() {//提交5个business.sub方法任务到单个线程池中
             public void run() {
                 for (int i = 0; i < 5; i++) {
                     business.sub();
                 }
             }

         });

         for (int i = 0; i < 5; i++) {//执行5次business.main方法
             business.main();
         }
     }

 }

 class Business2 {
     Lock lock = new ReentrantLock();
     Condition condition = lock.newCondition();//得到当前锁阻塞条件
     boolean isSub = true; //默认一开始限制性sub任务

     public void sub() {
         lock.lock();
         if (!isSub) {//不是sub执行条件，则进入进行阻塞处理
             try {
                 condition.await();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         try {
             for (int i = 0; i < 5; i++) {
                 System.out.println(Thread.currentThread().getName() + " : " + i);
             }
             isSub = false;
             condition.signal();
         } finally {
             lock.unlock();
         }

     }

     public void main() {
         lock.lock();
         if (isSub) { //是sub执行任务，则进入阻塞main任务
             System.out.println(Thread.currentThread().getName() + "-阻塞....main线程一开始就被阻塞了的");
             try {
                 condition.await();
             } catch (InterruptedException e) {
                 e.printStackTrace();
             }
         }
         try {
             for (int i = 0; i < 5; i++) {
                 System.out.println(Thread.currentThread().getName() + " : " + i);
             }
             isSub = true;
             condition.signal();
         } finally {
             lock.unlock();
         }
     }
 }