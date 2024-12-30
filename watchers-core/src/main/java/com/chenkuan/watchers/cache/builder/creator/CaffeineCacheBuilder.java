package com.chenkuan.watchers.cache.builder.creator;

import com.chenkuan.watchers.AbstractCaffeineCacheProxy;
import com.chenkuan.watchers.CacheExampleBuilder;
import com.chenkuan.watchers.CacheConfig;
import com.chenkuan.watchers.CacheExampleBuilderFactory;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author chenkuan
 **/

public class CaffeineCacheBuilder implements CacheExampleBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() {
        CacheExampleBuilderFactory.register(AbstractCaffeineCacheProxy.class, this);
    }

    @Override
    public Object buildCache(CacheConfig cacheConfig) {
        return Caffeine.newBuilder()
                .initialCapacity(cacheConfig.getInitializeSize())
                .maximumSize(cacheConfig.getMaxSize())
                .expireAfterAccess(cacheConfig.getExpireAfterAccess())
                .expireAfterWrite(cacheConfig.getExpireAfterWrite())
                .build();
    }

}
