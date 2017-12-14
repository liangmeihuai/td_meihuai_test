package com.common;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by tend on 2017/12/13.
 */
public class JAVAEightTest {
    public static void main(String[] args) {
        List<Map<String,Object>> list =  new ArrayList<>();
        list.stream().forEach(m ->{
            System.out.println("xxx");
        });

//        Map<String,Object> map = new HashMap<>();
//        map.put("id",1);
//        Map<String,Object> map1 = new HashMap<>();
//        map1.put("id",2);
//        Map<String,Object> map2 = new HashMap<>();
//        map2.put("id",3);
//        list.add(map);
//        list.add(map1);
//        list.add(map2);
//        List<Integer> list1 = list.stream().map(f -> (Integer) map.get("id")+(Integer)f.get("id")).collect(Collectors.toList());
//        System.out.println(list1);
    }
}
