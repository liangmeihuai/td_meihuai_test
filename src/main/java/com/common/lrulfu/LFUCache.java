package com.common.lrulfu;

import java.util.HashMap;
import java.util.Iterator;  
  
//LFU实现  
public class LFUCache<K,V> extends AbstractCacheMap<K, V> {  
      
  
    public LFUCache(int cacheSize, long defaultExpire) {  
        super(cacheSize, defaultExpire);  
        cacheMap = new HashMap<K, CacheObject<K,V>>(cacheSize+1) ;  
    }  
  
    /** 
     * 实现删除过期对象 和 删除访问次数最少的对象  
     *  
     */  
    @Override  
    protected int eliminateCache() {  
        Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();  
        int count  = 0 ;  
        long minAccessCount = Long.MAX_VALUE;
        while(iterator.hasNext()){  
            CacheObject<K, V> cacheObject = iterator.next();  
              
            if(cacheObject.isExpired() ){  
                iterator.remove();   
                count++ ;  
                continue ;  
            }else{  
                minAccessCount  = Math.min(cacheObject.accessCount , minAccessCount)  ;  
            }  
        }  
        // 调用eliminateCache方法时候肯定是缓存已经满了，如果能把过期的删除掉了，那么直接返回count
        if(count > 0 ) return count ;  
          
        if(minAccessCount != Long.MAX_VALUE ){  
              
            iterator = cacheMap.values().iterator();  
              
            while(iterator.hasNext()){  
                CacheObject<K, V> cacheObject = iterator.next();  
                // 实际上只有put的时候缓存满了会调用这个eliminate方法，而在put时候加了写锁，读锁是无法访问的，最后最终缓存中其实
                // 有且只有一个元素小于等于minAccessCount
                // 每个元素都减去被删除的一个最小值，可能更加合理一点，防止刚刚加进去的访问次数为0，很快又被删除掉
                cacheObject.accessCount  -=  minAccessCount ;  
                  
                if(cacheObject.accessCount <= 0 ){  
                    iterator.remove();  
                    count++ ;  
                }  
                  
            }  
              
        }  
          
        return count;  
    }  
  
}  