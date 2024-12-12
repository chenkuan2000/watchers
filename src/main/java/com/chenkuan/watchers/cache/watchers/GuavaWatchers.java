package com.chenkuan.watchers.cache.watchers;


import com.google.common.cache.Cache;

/**
 * @author chenkuan
 */


public class GuavaWatchers extends Watchers {

    private final String name;
    private final Cache<?, ?> guavaCache;
    private final CacheConfig cacheConfig;

    public GuavaWatchers(String name, Cache<?, ?> guavaCache, CacheConfig cacheConfig) {
        this.name = name;
        this.guavaCache = guavaCache;
        this.cacheConfig = cacheConfig;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Long getSize() {
        return cacheConfig.getMaxSize();
    }

    @Override
    public CacheConfig getConfig() {
        return cacheConfig;
    }

}
