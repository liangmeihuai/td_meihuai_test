package com.td.test.meihuai.great;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
/**
 *
 * Simple to Introduction
 * @ProjectName:  [${project_name}]
 * @Package:      [${package_name}.${file_name}]
 * @ClassName:    [${type_name}]
 * @Description:  [一句话描述该类的功能]
 * @Author:       [${user}]
 * @CreateDate:   [${date} ${time}]
 * @UpdateUser:   [${user}]
 * @UpdateDate:   [${date} ${time}]
 * @UpdateRemark: [说明本次修改内容]
 * @Version:      [v1.0]
 *
 */
/**
 * Created by tend on 2018/3/16 ==>类描述
 * @ahuor
 * @date
 * @author
 * @date
 * @version
 * @since
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
     * javadoc例子
     * <p><blockquote><pre>
     *     char data[] = {'a', 'b', 'c'};
     *     String str = new String(data);
     * </pre></blockquote><p>
     * Allocates a new {@code String} that contains characters from a subarray
     * of the <a href="Character.html#unicode">Unicode code point</a> array
     * 判段cache的总容量大小是否已经满了，true说明已经满了，false说明没满
     * @return 返回值是true，说明改缓存已经达到它的容量了，返回false则说明
     *          缓存当前所存的缓存个数还没有达到缓存的最大容量
     *          as determined by the {@link #equals(Object)} method.
     */
    @Override
    public boolean isFull(){
        if (cacheSize == 0){// 如果容量没有限制，那么就永远不会满
            return false;
        }
        // 如果设置了cacheSize那么则需要注意的就是比较当前缓存的大小和cacheSize的关系
        return cacheMap.size() >= cacheSize;
    }

    /**
     * 判断缓存是否已经空了,true是，false不是
     * @return true说明缓存里面没有数据了，当前为空，false说明缓存里面有数据，不为空
     */
    @Override
    public boolean isEmpty(){
        return cacheMap.size() == 0;
    }

    /**
     * version版本的说明
     * 兼容性位 1，表示兼容性，如果 +1 了说明这个修改是不兼容的
     * 特性位 2，表示已引入了两个特性，每次 +1 说明引入一个新特性
     * 缺陷修复位 3，表示已经修复了 3 个缺陷，每次 +1 说明修复了一个缺陷
     * 重构位 4，表示已经进行了 4 次重构，每次 +1 说明重构了一次
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * 根据给定的key值，取到缓存对应的value值
     * @param key 缓存的key
     * @return 根据key取到缓存的value值，如果缓存为null或者缓存已经失效，则返回null
     *          否则返回key值对应缓存的value值
     *
     * @throws ...XXXX
     *
     * @author 创建人
     * @date 创建日期
     * @author 修改人
     * @date 修改日期
     * @version 1.2.3.4
     * @since 1.0
     */
    @Override
    public V get(K key){
        readLock.lock();
        try {
            CacheObject<K, V> co = cacheMap.get(key);
            if (co == null ){
                return null;
            }
            if (co.isExpired()){
                cacheMap.remove(key);
            }
            return co.getCacheObject();
        }finally {
            readLock.lock();
        }

    }


}
