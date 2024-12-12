package com.chenkuan.watchers.cache.builder.watchers;


import com.chenkuan.watchers.cache.factory.WatchersBuilderFactory;
import com.chenkuan.watchers.cache.watchers.CacheConfig;
import com.chenkuan.watchers.cache.watchers.GuavaWatchers;
import com.chenkuan.watchers.cache.watchers.Watchers;
import com.google.common.cache.Cache;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author chenkuan
 */


@Component
public class GuavaWatchersBuilder implements WatchersBuilder, InitializingBean {



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
