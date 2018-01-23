package com.study.threadlocal;

import com.common.reflect.Person;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 弱引用比软引用还要弱，在系统GC时候，只要发现弱引用，不管系统堆空间使用情况如何，都会将对象回收
 * 该类演示了弱引用的这种性质
 * @author lhever 2017年4月4日 下午10:04:26
 * @version v1.0
 */
public class WeakRef
{

    public static class Student
    {
        public int id;
        public String name;

        public Student(Integer id, String name)
        {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString()
        {
            return "[id=" + id + ",name=" + name + "]";
        }
    }

    public static void main(String... args) throws InterruptedException
    {
        Student u = new Student(1, "alexzanda");
        WeakReference<Student> studentWeakRef = new WeakReference<Student>(u);
//        u = null;
        System.out.println(studentWeakRef.get());

        System.gc();
        System.out.println("After Gc:");
        System.out.println(studentWeakRef.get());//gc之后一定会被回收

        List<Person> list = new ArrayList<>();
        list.add(null);
        list.add(null);
        list.add(null);
        list.add(null);
        System.out.println(list.get(0) == null);
    }

}