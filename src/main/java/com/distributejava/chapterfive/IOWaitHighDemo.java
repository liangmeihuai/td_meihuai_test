package com.distributejava.chapterfive;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;

/**
 * Created by tend on 2018/1/29.
 */
public class IOWaitHighDemo {
    private String fileName = "/home/hadoop/meihuai.liang/distributeJava/7835_sy.log";
    private static int threadCount = Runtime.getRuntime().availableProcessors();
    private Random random = new Random();
    public static void main(String[] args) throws Exception {
        if (args.length == 1){
            threadCount = Integer.parseInt(args[1]);
        }
        IOWaitHighDemo demo = new IOWaitHighDemo();
        demo.runTest();
    }
    private void runTest() throws Exception{
        File file = new File(fileName);
        file.createNewFile();
        for (int i = 0; i < threadCount; i++){
            new Thread(new Task()).start();
        }
    }
    class  Task implements Runnable{
        @Override
        public void run() {
            while (true) {
                try{
                    BufferedWriter writer  = new BufferedWriter(new FileWriter(fileName, true));
                    StringBuilder stringBuilder = new StringBuilder("====begin====\n");
                    String threadName = Thread.currentThread().getName();
                    for (int i = 0; i < 100000; i++) {
                        stringBuilder.append(threadName);
                        stringBuilder.append("\n");
                    }
                    stringBuilder.append("====end====\n");
                    writer.write(stringBuilder.toString());
                    writer.close();
                    Thread.sleep(random.nextInt(10));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
