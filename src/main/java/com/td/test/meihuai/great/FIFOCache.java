package com.td.test.meihuai.great;

import com.sun.tools.jdi.LinkedHashMap;

import java.util.Iterator;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/21 10:51
 * @since 1.0
 */
public class FIFOCache<K, V> extends AbstractCacheMap<K, V>{
    public FIFOCache(long defaultExpire, int cacheSize) {
        super(defaultExpire, cacheSize);
        this.cacheMap = new LinkedHashMap(cacheSize + 1);
    }
    @Override
    protected int eliminateCache() {
        int count = 0;
        K firstKey = null;
        Iterator<CacheObject<K, V>> coIter = cacheMap.values().iterator();
        while (coIter.hasNext()){
            CacheObject<K, V> co = coIter.next();
            if (co.isExpired()){
                coIter.remove();
                count ++;
            }else {
                if (firstKey == null)
                    firstKey = co.key;
            }
        }
        if (firstKey != null && isFull()){
            this.cacheMap.remove(firstKey);
            count ++;
        }
        return count;
    }
}
