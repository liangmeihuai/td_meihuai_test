package com.deep.jvm.chaptersix;

import java.util.Arrays;

/**
 * Created by tend on 2018/2/23.
 */
public class AsciiTest {
    private static final String hexStrs = "43 6f 64 65";
    public static void main(String[] args) {
//        int ascii = '<';
//        System.out.println("ascii = [ " + ascii + " ]");
        System.out.println("decArray is " + Arrays.deepToString(hexArrayToDecArray(hexStrs)));
    }


    private static  Integer[] hexArrayToDecArray(String hexStrs){
        if (hexStrs == null){
            throw new IllegalArgumentException("hexArray cannot be null or length is 0.");
        }
        String[] hexArray = hexStrs.split(" ");
        // 从来存储输出的10进制数字符数组
        Integer[] decArray = new Integer[hexArray.length];
        for (int i = 0; i < hexArray.length; i ++){
            decArray[i] = Integer.valueOf(hexArray[i], 16);
        }
        return decArray;
    }
}
