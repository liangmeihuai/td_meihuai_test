package com.common.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by tend on 2018/3/13.
 */
public class IteratorTest {
    private static final List<String> list =  new ArrayList<String>();

    public static void main(String[] args) throws InterruptedException {
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("eee");
        list.add("fff");
        list.add("ggg");
        for (String str: list){
            TimeUnit.SECONDS.sleep(2);
            System.out.println("str = [ " + str + " ]");
            list.remove("aaa");
        }
//        for (int i = 0; i < list.size();){
//            System.out.println("str = [ " + list.get(i));
//            list.remove(i);
//        }

//        Iterator<String> iterator = list.iterator();
//        while (iterator.hasNext()){
//            String str = iterator.next();
//            System.out.println("str = [ " + str + " ]");
//           iterator.remove();
//        }
//        System.out.println(list.size());


    }
}
