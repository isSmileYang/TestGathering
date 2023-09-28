package com.multiTest.demo.cacheTests;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author yangxiaoxu
 * @description
 * @date 2023/9/28 16:57
 */
public class LRUCache {
    private int capacity;
    private static LinkedHashMap<Integer, Integer> cache;

    public LRUCache(int capacity){
        this.capacity = capacity;
        this.cache = new LinkedHashMap<Integer, Integer>(capacity, 0.75f,true){
            //当前元素数量超过容量时返回true
            protected boolean removeEldestEntry(Map.Entry<Integer, Integer> eldest) {
                return LRUCache.cache.size() > LRUCache.this.capacity;
            }
        };
    }
    public int get(int key) {
        return cache.getOrDefault(key, -1);
    }

    public void put(int key, int value) {
        cache.put(key, value);
    }
    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1));       // 返回 1
        cache.put(3, 3);    // 该操作会使得关键字 2 作废
        System.out.println(cache.get(2));       // 返回 -1 (未找到)
        cache.put(4, 4);    // 该操作会使得关键字 1 作废
        System.out.println(cache.get(1));       // 返回 -1 (未找到)
        System.out.println(cache.get(3));       // 返回 3
        System.out.println(cache.get(4));       // 返回 4
    }
}
