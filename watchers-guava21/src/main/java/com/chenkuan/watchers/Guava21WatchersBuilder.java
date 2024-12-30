package com.chenkuan.watchers;


import com.google.common.cache.Cache;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author chenkuan
 */

public class Guava21WatchersBuilder implements GuavaWatchersBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() {
        WatchersBuilderFactory.register(Cache.class, this);
    }

    @Override
    public Watchers buildWatchers(String beanName, Object cacheBean, CacheConfig cacheConfig) {

        Cache<?, ?> cache = (Cache<?, ?>) cacheBean;

        return new GuavaWatchers(beanName, cache, cacheConfig);
    }
}
