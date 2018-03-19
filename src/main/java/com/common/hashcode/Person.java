package com.common.hashcode;

/**
 * Created by tend on 2018/3/16.
 */
public class Person {
    private int id;
    private String name = "aaa";

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return name.equals(person.name);

    }

    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();
        System.out.println(p1.hashCode());
        System.out.println(p2.hashCode());
        System.out.println(p1 .equals(p2) );
    }
}
