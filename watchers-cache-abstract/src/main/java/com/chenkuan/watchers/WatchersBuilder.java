package com.chenkuan.watchers;



/**
 * @author chenkuan
 */


public interface WatchersBuilder {

    Watchers buildWatchers(String beanName, Object cacheBean , CacheConfig cacheConfig);

}
