package com.distributejava.chapterfive;

import java.util.ArrayList;

/**
 * Created by tend on 2018/1/25.
 */
public class Demo {
    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        demo.runTest();
    }

    private void runTest() throws Exception {
        int count = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < count; i++) {
            new Thread(new ConsumerCPUTask()).start();
        }
        for (int i = 0; i < 200; i++){
            new Thread(new NotConsumerCPUTask()).start();
        }

    }

    class ConsumerCPUTask implements  Runnable {

        @Override
        public void run() {
            String str = "dwelkjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjffffffffffffffffffffffffffffffff" +
                    "ffffffffffffffffffffffffffffffffffffffffffffffffffff#ffffffffffffffffffffffffffffffffffffffffffffffffffff";
            float i = 0.002f;
            float j = 232.13243f;
            while (true) {
                j = i * j;
                str.indexOf("#");
                ArrayList<String> list = new ArrayList<>();
                for (int k = 0; k < 10000; k++) {
                    list.add(str + String.valueOf(k));
                }
                list.contains("iii");
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class NotConsumerCPUTask implements  Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10000000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
