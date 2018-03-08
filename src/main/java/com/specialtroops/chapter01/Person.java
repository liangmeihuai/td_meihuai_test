package com.specialtroops.chapter01;

/**
 * Created by tend on 2018/3/8.
 */
public class Person {
    private int id;
    private String name;

    public Person(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public Person() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (id != person.id) return false;
        return name != null ? name.equals(person.name) : person.name == null;

    }

//    @Override
//    public int hashCode() {
////        int result = id;
////        result = 31 * result + (name != null ? name.hashCode() : 0);
////        return result;
//        return 1;
//    }


}
