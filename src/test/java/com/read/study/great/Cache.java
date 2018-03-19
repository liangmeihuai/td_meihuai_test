package com.read.study.great;

/**
 * Created by tend on 2018/3/16.
 */
public interface Cache<K, V> {
    int size();// 当前所放缓存的大小
    int cacheSize();// 缓存的总容量大小
    void put(K key, V value, long expire);
    void put(K key, V value);
    int eliminate();//删除缓存并且返回删除缓存的个数
    V get(K key);
    boolean isFull();//缓存是否已经满了，达到-==cacheSize了
    long getDefaultExpire();// 返回默认存活时间
    boolean isEmpty(); //缓存是否已经空了
    void clear();// 情况所有缓存对象
}
