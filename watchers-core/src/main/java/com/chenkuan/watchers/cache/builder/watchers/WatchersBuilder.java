package com.chenkuan.watchers.cache.builder.watchers;


import com.chenkuan.watchers.cache.watchers.CacheConfig;
import com.chenkuan.watchers.cache.watchers.Watchers;

/**
 * @author chenkuan
 */


public interface WatchersBuilder {

    Watchers buildWatchers(String beanName, Object cacheBean , CacheConfig cacheConfig);

}
