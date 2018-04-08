package com.common.lrulfu;

import java.util.Iterator;
import java.util.LinkedHashMap;  
import java.util.Map;  
  
/**  
 * LRU  实现  
 * @author Wen  
 *  
 * @param <K>  
 * @param <V>  
 */  
public class LRUCache<K, V> extends AbstractCacheMap<K, V> {
    public LRUCache(int cacheSize, long defaultExpire) {
        super(cacheSize , defaultExpire) ;
        //linkedHash已经实现LRU算法 是通过双向链表来实现，当某个位置被命中，通过调整链表的指向将该位置调整到头位置，
        // 新加入的内容直接放在链表头，如此一来，最近被命中的内容就向链表头移动，需要替换时，
        // 链表最后的位置就是最近最少使用的位置
        this.cacheMap = new LinkedHashMap<K, CacheObject<K, V>>( cacheSize +1 , 1f,true ) {  
  
            @Override  
            protected boolean removeEldestEntry(  
                    Map.Entry<K, CacheObject<K, V>> eldest) {  
  
                return LRUCache.this.removeEldestEntry(eldest);  
            }  
  
        };  
    }  
  
    private boolean removeEldestEntry(Map.Entry<K, CacheObject<K, V>> eldest) {  
  
        if (cacheSize == 0)  
            return false;  
  
        return size() > cacheSize;  
    }  
  
    /**  
     * 只需要实现清除过期对象就可以了,linkedHashMap已经实现LRU  
     */  
    @Override  
    protected int eliminateCache() {  
  
        if(!isNeedClearExpiredObject()){ return 0 ;}  
          
        Iterator<CacheObject<K, V>> iterator = cacheMap.values().iterator();  
        int count  = 0 ;  
        while(iterator.hasNext()){  
            CacheObject<K, V> cacheObject = iterator.next();  
              
            if(cacheObject.isExpired() ){  
                iterator.remove();   
                count++ ;  
            }  
        }  
          
        return count;  
    }

    public static void main(String[] args) {
        Cache<String, String> cache = new LRUCache<>(10, 0);
        cache.put("key","value");
        cache.put("key1","value");
        cache.put("key2","value");
        cache.put("key3","value");
        cache.put("key4","value");
//        cache.put("key5","value");
//        cache.put("key6","value");
//        cache.put("key7","value");
//        cache.put("key8","value");
//        cache.put("key9","value");
//        cache.put("key10","value");
//        cache.put("key11","value");
//        cache.put("key12","value");
//        cache.put("key13","value");
//        cache.put("key14","value");

        System.out.println(cache.get("key"));
        System.out.println(cache.getCacheSize());
        System.out.println(cache.size());

    }
  
}  