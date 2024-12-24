package com.chenkuan.watchers.cache.watchers;


/**
 * @author chenkuan
 */


public abstract class Watchers {

    public abstract String getName();

    public abstract Long getSize();

    public abstract CacheConfig getConfig();
}
