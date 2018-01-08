package com.deep.jvm.chaptertwo;

/**
 * Created by tend on 2018/1/8.
 *-Xss 128k
 */
public class JavaVMStackSOF {
    private static int stackLength = 1;
    public static void stackLeak(){
        stackLength ++;
        stackLeak();
    }

    public static void main(String[] args){
        try{
            stackLeak();
        }catch (Throwable throwable){
            System.out.println("when leak the stackLenghth is [" + stackLength + "]");
            throwable.printStackTrace();
        }
    }
}
