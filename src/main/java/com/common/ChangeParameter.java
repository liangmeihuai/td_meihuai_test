package com.common;

/**
 * Created by tend on 2018/3/19.
 */
public class ChangeParameter {
    public static void main(String[] args) {
        System.out.println(changeParams(100));
        System.out.println(changeParams());
    }
    private static int changeParams(int ... a){
        if (a.length > 0){
            return a[0];
        }
        return 0;
    }
}
