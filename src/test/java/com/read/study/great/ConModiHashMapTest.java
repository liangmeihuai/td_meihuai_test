package com.read.study.great;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by tend on 2018/3/19.
 */
public class ConModiHashMapTest {
    private static final Map<Integer, String> map = new HashMap<>();
    public static void main(String[] args) {
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        new Thread(new Runnable() {
            @Override
            public void run() {
                getAndRemoveHashMap();
            }
        }).start();
        getAndRemoveHashMap();

    }
    private static void getAndRemoveHashMap(){
        System.out.println(Thread.currentThread().getName() + "..value is.. " + map.get(1));
        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String removeValue = map.remove(1);
        System.out.println(Thread.currentThread().getName() + "..remove is .. " + removeValue);
    }

}
