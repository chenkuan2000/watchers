package com.chenkuan.watchers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Policy;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * @author chenkuan
 */

public class Caffeine2CacheProxy extends AbstractCaffeineCacheProxy implements Cache<Object, Object>{

    private Cache<Object, Object> cache;

    @SuppressWarnings("unchecked")
    public Caffeine2CacheProxy(Cache<?, ?> cache) {
        this.cache = (Cache<Object, Object>) cache;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void replaceCache(Object newCache) {
        this.cache = (Cache<Object, Object>) newCache;
    }


    @Override
    public @Nullable Object getIfPresent(@NonNull Object key) {
        return cache.getIfPresent(key);
    }

    @Override
    public @Nullable Object get(@NonNull Object key, @NonNull Function<? super Object, ?> mappingFunction) {
        return cache.get(key, mappingFunction);
    }

    @Override
    public @NonNull Map<@NonNull Object, @NonNull Object> getAllPresent(@NonNull Iterable<@NonNull ?> keys) {
        return cache.getAllPresent(keys);
    }

    @Override
    public void put(@NonNull Object key, @NonNull Object value) {
        cache.put(key, value);
    }

    @Override
    public void putAll(@NonNull Map<?, ?> map) {
        cache.putAll(map);
    }

    @Override
    public void invalidate(@NonNull Object key) {
        cache.invalidate(key);
    }

    @Override
    public void invalidateAll(@NonNull Iterable<?> keys) {
        cache.invalidateAll(keys);
    }

    @Override
    public void invalidateAll() {
        cache.invalidateAll();
    }

    @Override
    public @NonNegative long estimatedSize() {
        return cache.estimatedSize();
    }

    @Override
    public @NonNull CacheStats stats() {
        return cache.stats();
    }

    @Override
    public @NonNull ConcurrentMap<@NonNull Object, @NonNull Object> asMap() {
        return cache.asMap();
    }

    @Override
    public void cleanUp() {
        cache.cleanUp();
    }

    @Override
    public @NonNull Policy<Object, Object> policy() {
        return cache.policy();
    }

    public Cache<Object, Object> getCache() {
        return cache;
    }
}



