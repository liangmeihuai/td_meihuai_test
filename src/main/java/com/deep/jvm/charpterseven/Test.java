package com.deep.jvm.charpterseven;

public class Test {
    static {
        i = 2;  //  给变量复制可以正常编译通过
//        System.out.print(i);  // 这句编译器会提示“非法向前引用”  // illegal forward reference
    }
    static int i = 1;
}

