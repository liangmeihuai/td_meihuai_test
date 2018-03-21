package com.td.test.meihuai.great;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author Created by meihuai.liang
 * @version 1.0.0.0
 * @date 2018/3/21 11:42
 * @since 1.0
 */
public class LRUCache<K, V> extends AbstractCacheMap<K, V>{
    public LRUCache(long defaultExpire, int cacheSize) {
        super(defaultExpire, cacheSize);
        cacheMap = new LinkedHashMap<K, CacheObject<K, V>>(cacheSize + 1, 1f, true){
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, CacheObject<K, V>> eldest) {
                return LRUCache.this.removeEldestEntry();// 访问外部类的方法
            }
        };
    }

    /**
     * 是否要删除最老的元素，也就是表尾元素
     * @return
     */
    private boolean removeEldestEntry(){
        if (cacheSize == 0)
            return false;
        return size() >= cacheSize;
    }

    @Override
    protected int eliminateCache() {
        if (!isNeedClearExpiredObject())
            return 0;
        int count = 0;
        Iterator<CacheObject<K, V>> coIter = cacheMap.values().iterator();
        while (coIter.hasNext()){
            CacheObject<K, V> co = coIter.next();
            if (co.isExpired()){
                coIter.remove();
                count ++;
            }
        }
        return count;
    }
}
