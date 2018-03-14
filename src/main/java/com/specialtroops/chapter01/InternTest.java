package com.specialtroops.chapter01;

/**
 * Created by tend on 2018/3/8.
 */
public class InternTest {
    public static void main(String[] args) {

//        String a = "ayujj";
//        String b = "ayujj";
//        System.out.println(a == a.intern());
//        System.out.println(b == b.intern());

        String h = "ayujj";
        String a = new String("ayujj");
        String b = new String("ayujj");
        System.out.println(a == a.intern());
        System.out.println(b == b.intern());
        System.out.println(h == b.intern());
//        System.out.println(b.intern().hashCode());
//        System.out.println(b.hashCode());
//        System.out.println(h.hashCode());
        System.out.println(System.identityHashCode(b.intern()));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(h));

        // 注意加append和不加append-2
//        String str1 = new StringBuilder("ayujj").toString();
//        System.out.println(str1.intern() == str1);
//        String str2 = new StringBuilder("ayujj").toString();
//        System.out.println(str2.intern() == str2);


//        String str1 = new StringBuilder("ayu").append("jj").toString();
//        System.out.println(str1.intern() == str1);
//        String str2 = new StringBuilder("ayu").append("jj").toString();
//        System.out.println(str2.intern() == str2);

//        String str1 = new StringBuilder("计算机").append("软件").toString();
//        System.out.println(str1.intern() == str1);
//        String str2 = new StringBuilder("ja").append("va").toString();
//        System.out.println(str2.intern() == str2);
    }
}
