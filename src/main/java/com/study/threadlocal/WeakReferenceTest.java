package com.study.threadlocal;

import com.common.reflect.Person;

import java.lang.ref.WeakReference;

/**
 * Created by tend on 2018/1/18.
 */
public class WeakReferenceTest {
    public static void main(String[] args) {
        Person person = new Person();
        System.out.println(person.hashCode());

        WeakReference<Person> weakReference = new WeakReference<Person>(person);
        System.out.println(weakReference.get().hashCode());

    }
}
