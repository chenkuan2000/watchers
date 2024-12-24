package com.chenkuan.watchers.cache.builder.watchers;


import com.chenkuan.watchers.cache.aspect.WatchAspect;
import com.chenkuan.watchers.cache.factory.WatchersBuilderFactory;
import com.chenkuan.watchers.cache.watchers.CacheConfig;
import com.chenkuan.watchers.cache.watchers.CaffeineWatchers;
import com.chenkuan.watchers.cache.watchers.Watchers;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Policy;
import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;

import java.util.concurrent.TimeUnit;

/**
 * @author chenkuan
 */

@Data
@AutoConfigureAfter(WatchAspect.class)
public class CaffeineWatchersBuilder implements WatchersBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() {
        WatchersBuilderFactory.register(Cache.class, this);
    }

    @Override
    public Watchers buildWatchers(String beanName, Object cacheBean, CacheConfig cacheConfig) {

        Cache<?, ?> cache = (Cache<?, ?>) cacheBean;

        return new CaffeineWatchers(beanName, cache, cacheConfig);
    }
}
