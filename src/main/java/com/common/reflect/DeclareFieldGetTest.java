package com.common.reflect;

import java.lang.reflect.Field;

/**
 * Created by tend on 2018/1/9.
 */
public class DeclareFieldGetTest {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, InstantiationException {
        Field field = Person.class.getDeclaredField("i");
        field.setAccessible(true);
        Object obj = field.get(new Person());
        System.out.println(obj);
    }
}