package com.chenkuan.watchers;


import com.github.benmanes.caffeine.cache.Cache;

/**
 * @author chenkuan
 */


public class CaffeineWatchers extends Watchers {

    private final String name;
    private final Cache<?, ?> caffeineCache;
    private final CacheConfig cacheConfig;

    public CaffeineWatchers(String name, Cache<?, ?> caffeineCache, CacheConfig cacheConfig) {
        this.name = name;
        this.caffeineCache = caffeineCache;
        this.cacheConfig = cacheConfig;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Long getSize() {
        return (caffeineCache.estimatedSize());
    }

    @Override
    public CacheConfig getConfig() {
        return cacheConfig;
    }

}
