package com.jdkeight;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/30 2:11
 * @since 1.0
 */
public class FunctionTest {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        List list2 = list.stream().map(x -> x + "1").collect(Collectors.toList());
        System.out.println(list2);
    }
}
