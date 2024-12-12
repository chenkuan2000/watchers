package com.chenkuan.watchers.cache.cache;


import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;

/**
 * @author chenkuan
 */


@Getter
public class CaffeineGuavaProxy extends AbstractGuavaCacheProxy {

    private Cache<Object, Object> cache;

    @SuppressWarnings("unchecked")
    public CaffeineGuavaProxy(Cache<?, ?> cache) {
        this.cache = (Cache<Object, Object>)cache;
    }


    @Override
    @SuppressWarnings("unchecked")
    public void replaceCache(Object newCache) {
        this.cache = (Cache<Object, Object>) newCache;
    }

    @Nullable
    @Override
    public Object getIfPresent(@NotNull Object key) {
        return cache.getIfPresent(key);
    }

    @NotNull
    @Override
    public Object get(@NotNull Object key, @NotNull Callable<?> loader) throws ExecutionException {
        return cache.get(key, loader);
    }

    @NotNull
    @Override
    public ImmutableMap<Object, Object> getAllPresent(@NotNull Iterable<?> keys) {
        return cache.getAllPresent(keys);
    }

    @Override
    public void put(@NotNull Object key, @NotNull Object value) {
        cache.put(key, value);
    }

    @Override
    public void putAll(@NotNull Map<?, ?> m) {
        cache.putAll(m);
    }

    @Override
    public void invalidate(@NotNull Object key) {
        cache.invalidate(key);
    }

    @Override
    public void invalidateAll(@NotNull Iterable<?> keys) {
        cache.invalidateAll(keys);
    }

    @Override
    public void invalidateAll() {
        cache.invalidateAll();
    }

    @Override
    public long size() {
        return cache.size();
    }

    @NotNull
    @Override
    public CacheStats stats() {
        return cache.stats();
    }

    @NotNull
    @Override
    public ConcurrentMap<Object, Object> asMap() {
        return cache.asMap();
    }

    @Override
    public void cleanUp() {
        cache.cleanUp();
    }
}
