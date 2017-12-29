package com.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tend on 2017/12/27.
 */
public class MemoryLimitedTest {
    private static final List list = new ArrayList<String>();
    private static final int INPUT_NUM = 10000000;
    public static void main(String[] args) {
        for (int i =0; i < INPUT_NUM;i++ ){
            list.add(new String("abc") + i);
            i++;
        }
    }
}
