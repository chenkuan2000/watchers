package com.chenkuan.watchers.cache.cache;


import com.chenkuan.watchers.AbstractCacheProxy;
import com.google.common.cache.Cache;

/**
 * @author chenkuan
 */


public abstract class AbstractGuavaCacheProxy extends AbstractCacheProxy implements Cache<Object, Object> {
    @Override
    public abstract void replaceCache(Object cache);
}
