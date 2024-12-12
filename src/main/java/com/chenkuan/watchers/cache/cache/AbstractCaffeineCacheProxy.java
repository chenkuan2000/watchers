package com.chenkuan.watchers.cache.cache;


import com.github.benmanes.caffeine.cache.Cache;

/**
 * @author chenkuan
 */


public abstract class AbstractCaffeineCacheProxy extends AbstractCacheProxy implements Cache<Object, Object> {

    @Override
    public abstract void replaceCache(Object cache);
}
