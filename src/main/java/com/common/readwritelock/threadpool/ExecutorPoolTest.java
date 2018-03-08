package com.common.readwritelock.threadpool;

import java.util.concurrent.ExecutorService;
     import java.util.concurrent.Executors;
   
     /**
      * Created by liangyongxing on 2017/3/8.
     */
    public class ExecutorPoolTest {
        public static void main(String[] args) {
            ExecutorService executorService = Executors.newCachedThreadPool();
//            ExecutorService executorService = Executors.newFixedThreadPool(3);
            try {
                for (int i = 1; i <= 10; i++) {  //向线程池提交10个任务
                    final int sequence = i;
                    //仔细品味runnable对象放到循环里面和外面的区别，为了让每个对象有自己独立的编号
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 1; i <= 2; i++) {
                                //为了观察打印效果需要设置一定的休眠
                                try {
                                    Thread.sleep(200);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                System.out.println(String.format("%s is serving %d task loop of %d.",
                                        Thread.currentThread().getName(), sequence, i));
                            }
                        }
                    });
                }
                /**
                 * 用下面这句代码来说明上面的代码是在提交任务，并且所有的任务都已经提交了，但任务是什么时候执行的，则是由线程池调度的！
                 */
                System.out.println("all task have committed!");
            } finally {
                //注意与executorService.shutdownNow()的区别。
                executorService.shutdown();
            }
        }
    }