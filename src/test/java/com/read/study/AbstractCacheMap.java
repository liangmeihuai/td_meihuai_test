package com.read.study;

import com.common.lrulfu.Cache;

import java.util.Map;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by tend on 2018/3/13.
 * 很巧妙地把缓存对象作为内部类封装在AbstractCacheMap抽象类里面
 * 增删改查，判断容量，是否为null，是否满了
 */
public abstract class AbstractCacheMap<K, V> implements Cache<K,V> {

    class CacheObject<K2, V2>{
        // 属性
        final K2 key;
        final V2 cacheObject;
        long ttl;// 缓存失效时间(ttl默认为0表示永不失效)
        long lastAccessTime;// 缓存对象最后被访问的时刻
        int accessCount;// 缓存被访问的次数
        public CacheObject(K2 key, V2 cacheObject, long ttl) {
            this.key = key;
            this.cacheObject = cacheObject;
            this.ttl = ttl;
        }



        boolean isExpired(){
            if (ttl == 0){
                return false;
            }
            return lastAccessTime + ttl < System.currentTimeMillis();
        }

        V2 getObject(){
            lastAccessTime = System.currentTimeMillis();
            accessCount++;
            return cacheObject;
        }

    }
    // 你得用个集合包装缓存
    protected Map<K, CacheObject<K,V>> cacheMap;
    private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();

    // 缓存具有的一些属性
    private int cacheSize;// 缓存容量的大小限制,默认为0无限制
    private boolean existCustomerExpire;// 是否设置缓存失效时间
    private long defaultExpire;// 默认过期时间,0->表示永不过期

    // 实现增
    /**
     * 向缓存添加value对象,其在缓存中生存时间为默认值
     *defaultExpire 不为0，或者输入了一个默认的expire说明人为定制了expire的过期失效时间
     * @param key
     * @param value
     */
    @Override
    public void put(K key ,V value) {
        put(key, value, defaultExpire);
    }

    /**
     * 向缓存添加value对象,并指定存活时间
     * @param key
     * @param value
     * @param expire  过期时间
     */
    @Override
    public void put(K key ,V value , long expire ) {
        writeLock.lock();
        try{
            if (expire != 0){// 输入的缓存过期时间不为0，则
                existCustomerExpire = true;
            }
            CacheObject<K, V> co = new CacheObject<>(key, value, expire);
            if (isFull()){
                eliminate();
            }
            cacheMap.put(key, co);
        }finally {
            writeLock.unlock();
        }
    }
    // 检查缓存数据库是否有需要清除的缓存对象(必须有缓存对象设置了过期时间，否则默认为0代表是永不清除，永不过期)
    public boolean isNeedClearExpireObject(){
        return (defaultExpire > 0 || existCustomerExpire);
    }
    public boolean isFull(){
        if (cacheSize == 0){// 缓存容量大小设置0，说明缓存的容量大小无限制
            return false;
        }
        return cacheMap.size() >= cacheSize;
    }

    /**
     * 返回被删除对象的大小
     *  /**
     * 返回当前缓存对象的大小
     *  //int size();
     * @return
     * 删除缓存对象的具体实现
     * 为何交给具体实现子类来实现这个删除对象方法，是因为
     * 每种缓存算法的实现规则都不一样，LRU,LFU,FIFO
     */
    protected abstract int eliminateCache();

    public int eliminate(){
        writeLock.lock();
        try{
            return eliminateCache();
        }finally {
            writeLock.unlock();
        }
    }
    public void remove(K key){
        writeLock.lock();
        try {
            cacheMap.remove(key);
        }finally {
            writeLock.unlock();
        }

    }
    @Override
    public V get(K key){
        readLock.lock();
        try {
            CacheObject<K, V> co = cacheMap.get(key);
            if (co == null){
                return null;
            }
            // 如果这个缓存对象失效了怎么办
            if (co.isExpired()){
                cacheMap.remove(key);
                return null;
            }
            return co.getObject();
        }finally {
            readLock.unlock();
        }
    }
}
