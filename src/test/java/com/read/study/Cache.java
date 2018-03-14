//package com.read.study;
//
///**
// * Created by tend on 2018/3/13.
// * 增删改查(缓存就是一个更加贴近于操作者的数据库)
// * 这个接口需要有一个抽象类去实现它，同时我们需要有一个缓存对象去封装缓存
// * Cache接口封装了方法，但是没有缓存的静态的东西(属性不变)
// */
//public interface Cache<K, V> {
//    // 返回整个缓存的大小
//    int size();
//    boolean isFull();
//    boolean isEmpty();
//    // 增(修改)
//    boolean put(K k, V v);// 默认的缓存失效时间
//    boolean put(K k, V v, long expirationTime);// expirationTime时间单位是ms
//    // 删除(定向删除)
//    void delete(K v);//
//    int eliminate();// 返回被删除缓存k的大小(根据lru或者lfu的规则删除)
//    // 查询
//    V get(K v);
//    long getDefaultExpirationTime();// 返回默认的缓存失效时间
//    int getCacheSize();// 返回已经存在的缓存的大小
//    /**
//     * 清除所有缓存对象
//     */
//    void clear();
//}
