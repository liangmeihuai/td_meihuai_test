package com.common.reflect;

import java.lang.reflect.Field;

/**
 * Created by tend on 2018/1/9.
 */
public class DeclareFieldGetTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field field = Person.class.getDeclaredField("i");
        field.setAccessible(true);
        Object obj = field.get(new Person());// 如果这个私有属性是静态的成员变量，则可以为 null,field.get(null);
        // 或者任意一个东西,the {@code obj} argument * is ignored
        System.out.println(obj);
    }
}