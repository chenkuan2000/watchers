package com.chenkuan.watchers;


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkuan
 */


public class WatchersFactory {

    private static final Map<String, Watchers> CACHE_WATCHERS_MAP = new HashMap<>();

    public static void registerCacheWatchers(String cacheName, Watchers watchers) {
        CACHE_WATCHERS_MAP.put(cacheName, watchers);
    }

    public static Watchers getCacheWatchers(String cacheName) {
        return CACHE_WATCHERS_MAP.get(cacheName);
    }

    public static Map<String, Watchers> getCacheWatchers() {
        return CACHE_WATCHERS_MAP;
    }

    public static void removeCacheWatchers(String cacheName) {
         CACHE_WATCHERS_MAP.remove(cacheName);
    }

}
