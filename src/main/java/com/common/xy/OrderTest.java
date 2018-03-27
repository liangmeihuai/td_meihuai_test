package com.common.xy;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/27 10:53
 * @since 1.0
 */
public class OrderTest {

    private int i = 5;
    public OrderTest(){
        System.out.println("构造函数");
    }
    {
        System.out.println("构造代码块");
    }

    public static void main(String[] args) {
        new OrderTest();
    }
}
