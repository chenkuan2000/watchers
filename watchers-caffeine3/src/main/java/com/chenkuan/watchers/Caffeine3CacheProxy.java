package com.chenkuan.watchers;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Policy;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.checkerframework.checker.nullness.qual.PolyNull;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * @author chenkuan
 */

public class Caffeine3CacheProxy extends AbstractCaffeineCacheProxy implements Cache<Object, Object>{

    private Cache<Object, Object> cache;

    @SuppressWarnings("unchecked")
    public Caffeine3CacheProxy(Cache<?, ?> cache) {
        this.cache = (Cache<Object, Object>) cache;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void replaceCache(Object newCache) {
        this.cache = (Cache<Object, Object>) newCache;
    }

    @Override
    public @Nullable Object getIfPresent(Object var1) {
        return cache.getIfPresent(var1);
    }

    @Override
    public @PolyNull Object get(Object var1, Function<? super Object, ?> var2) {
        return cache.get(var1,var2);
    }

    @Override
    public Map<Object, Object> getAllPresent(Iterable<?> var1) {
        return cache.getAllPresent(var1);
    }

    @Override
    public Map<Object, Object> getAll(Iterable<?> var1, Function<? super Set<?>, ? extends Map<?, ?>> var2) {
        return cache.getAll(var1, var2);
    }

    @Override
    public void put(Object var1, Object var2) {
        cache.put(var1, var2);
    }

    @Override
    public void putAll(Map<?, ?> var1) {
        cache.putAll(var1);
    }

    @Override
    public void invalidate(Object var1) {
        cache.invalidate(var1);
    }

    @Override
    public void invalidateAll(Iterable<?> var1) {
        cache.invalidateAll(var1);
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
    public CacheStats stats() {
        return cache.stats();
    }

    @Override
    public ConcurrentMap<Object, Object> asMap() {
        return cache.asMap();
    }

    @Override
    public void cleanUp() {
        cache.cleanUp();
    }

    @Override
    public Policy<Object, Object> policy() {
        return cache.policy();
    }

    public Cache<Object, Object> getCache() {
        return cache;
    }
}
