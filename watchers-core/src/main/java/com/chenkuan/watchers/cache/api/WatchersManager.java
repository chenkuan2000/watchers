package com.chenkuan.watchers.cache.api;

import com.chenkuan.watchers.CacheConfig;
import com.chenkuan.watchers.Watchers;
import com.chenkuan.watchers.cache.watchers.WatchersRegistrar;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author chenkuan
 */

public class WatchersManager {

    private final WatchersRegistrar watchersRegistrar;

    public WatchersManager(WatchersRegistrar watchersRegistrar) {
        this.watchersRegistrar = watchersRegistrar;
    }

    public CacheConfig getDashboard(String cacheName) {
        Watchers cacheWatchers = getWatchers(cacheName);
        return cacheWatchers.getConfig();
    }

    public List<CacheConfig> getDashboard() {

        Map<String, Watchers> cacheWatchers = getWatchers();
        return cacheWatchers.values().stream()
                .map(Watchers::getConfig)
                .collect(Collectors.toList());

    }

    /**
     * 获取CacheWatchers
     */
    public Map<String, Watchers> getWatchers() {
        return watchersRegistrar.getWatchers();
    }

    /**
     * 获取CacheWatchers
     */
    public Watchers getWatchers(String cacheName) {
        return watchersRegistrar.getWatchers(cacheName);
    }

    /**
     * 替换cacheBean
     */
    public void replaceCache(String cacheName,CacheConfig cacheConfig) {
        watchersRegistrar.replaceCache(cacheName, cacheConfig);
    }

    /**
     * 判断CacheWatchers是否存在
     */
    public boolean existsWatchers(String cacheName) {
        return watchersRegistrar.existsWatchers(cacheName);
    }

}
