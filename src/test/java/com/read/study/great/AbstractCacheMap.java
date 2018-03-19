package com.read.study.great;

import com.sun.btrace.BTraceUtils;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by tend on 2018/3/16.
 */
public abstract class AbstractCacheMap<K, V> implements Cache<K, V>{
    class CacheObject<K2, V2>{// 缓存对象
        private K2 key;
        private V2 cacheObject;
        private long ttl;// 缓存存活时间
        private int accessCount;// 缓存访问次数
        private long lastAccess;// 缓存的最后访问时间

        public CacheObject(K2 key, V2 cacheObject, long ttl) {
            this.key = key;
            this.cacheObject = cacheObject;
            this.ttl = ttl;
//            this.accessCount = 1;// 默认这里被访问了一次,// 这里需要加吗，应该不需要
            this.lastAccess = System.currentTimeMillis();
        }

        boolean isExpired(){
            if (ttl == 0){
                return false;
            }
            return ttl + lastAccess < System.currentTimeMillis();
        }

        V2 getCacheObject(){
            accessCount ++;
            this.lastAccess = System.currentTimeMillis();
            return cacheObject;
        }
    }

    // 还有abstract的内部属性，需要一个容器来装缓存对象吧
    protected Map<K, CacheObject<K, V>> cacheMap;
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();
    protected long defaultExpire;
    protected int cacheSize;// 缓存大小
    protected boolean existCustomerExpire;// 是否存在用户定制的缓存失效时间

    // 这里面需要设置缓存的默认失效时间，和缓存的总容量大小
    public AbstractCacheMap(long defaultExpire, int cacheSize) {
        this.defaultExpire = defaultExpire;
        this.cacheSize = cacheSize;
    }

    public AbstractCacheMap() {
    }

    @Override
    public int cacheSize() {
        return cacheSize;
    }

    @Override
    public int size(){
        return cacheMap.size();
    }
    @Override
    public  void put(K key, V value, long expire){
        writeLock.lock();
        try{
            //要写进缓存，首先判断expire是否传进去的是0，决定这个缓存的属性值，existCustomerExpire
            if (expire != 0){
                existCustomerExpire = true;
            }
            // 还得看缓存是否已经满了，满了的话，则需要清楚缓存中过期的对象，腾出空间
            if (isFull()){
                eliminate();
            }
            // 包装缓存对象cacheObject
            CacheObject<K, V> co = new CacheObject<>(key, value, expire);
            cacheMap.put(key, co);
        }finally {
            writeLock.unlock();
        }
    }
    @Override
    public void put(K key, V value){
        put(key, value, defaultExpire);
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isFull(){
        if (cacheSize == 0){// 如果容量没有限制，那么就永远不会满
            return false;
        }
        // 如果设置了cacheSize那么则需要注意的就是比较当前缓存的大小和cacheSize的关系
        return cacheMap.size() >= cacheSize;
    }

    @Override
    public boolean isEmpty(){
        return cacheMap.size() == 0;
    }

}
