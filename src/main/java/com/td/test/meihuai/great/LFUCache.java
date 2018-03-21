package com.td.test.meihuai.great;

import javax.validation.constraints.Max;
import java.util.HashMap;
import java.util.Iterator;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/21 12:00
 * @since 1.0
 */
public class LFUCache<K, V> extends AbstractCacheMap<K, V>{
    public LFUCache(long defaultExpire, int cacheSize) {
        super(defaultExpire, cacheSize);
        cacheMap = new HashMap<K, CacheObject<K, V>>(cacheSize + 1);
    }

    @Override
    protected int eliminateCache() {
        int count = 0;
        int minAccessCount = Integer.MAX_VALUE;
        Iterator<CacheObject<K, V>> coIter = cacheMap.values().iterator();
        while (coIter.hasNext()){
           CacheObject<K, V> co = coIter.next();
            if (co.isExpired()){
                coIter.remove();
                count ++;
            }else {
                minAccessCount = Math.min(co.accessCount, minAccessCount);
            }
        }
        if (count > 0) return count;
        // 说明没有删除过期的缓存，需要移除掉访问次数最少的
        if (minAccessCount != Integer.MAX_VALUE){
            coIter = cacheMap.values().iterator();
            while (coIter.hasNext()){
                CacheObject<K, V> co = coIter.next();
                if (co.accessCount - minAccessCount <= 0){
                    coIter.remove();
                    count ++;
                }
            }
        }
        return count;
    }
}
