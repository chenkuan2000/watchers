package com.chenkuan.watchers;


import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Policy;
import org.springframework.beans.factory.InitializingBean;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


/**
 * @author chenkuan
 */

public class Caffeine2WatchersBuilder implements CaffeineWatchersBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() {
        WatchersBuilderFactory.register(Cache.class, this);
    }

    @Override
    public Watchers buildWatchers(String beanName, Object cacheBean, CacheConfig cacheConfig) {

        Cache<?, ?> cache = (Cache<?, ?>) cacheBean;

        Policy<?, ?> policy = cache.policy();

        Long initializeSize = cache.estimatedSize();
        Long maxSize = policy.eviction().map(Policy.Eviction::getMaximum).orElse(null);
        Long expireAfterAccess = policy.expireAfterAccess().map(expiration -> expiration.getExpiresAfter(TimeUnit.SECONDS)).orElse(null);
        Long expireAfterWrite = policy.expireAfterWrite().map(expiration -> expiration.getExpiresAfter(TimeUnit.SECONDS)).orElse(null);
        TimeUnit timeUnit = TimeUnit.MINUTES;
        Boolean expireVariably = cache.policy().expireVariably().isPresent();

        if (cacheConfig == null) {

            cacheConfig = CacheConfig.builder()
                    .cacheName(beanName)
                    .initializeSize(initializeSize.intValue())
                    .maxSize(maxSize)
                    .expireAfterAccess(expireAfterAccess != null ? Duration.ofMillis(expireAfterAccess) : null)
                    .expireAfterWrite(expireAfterWrite != null ? Duration.ofMillis(expireAfterWrite) : null)
                    .timeUnit(timeUnit)
                    .expireVariably(expireVariably)
                    .build();
        }

        return new CaffeineWatchers(beanName, cache, cacheConfig);
    }
}
