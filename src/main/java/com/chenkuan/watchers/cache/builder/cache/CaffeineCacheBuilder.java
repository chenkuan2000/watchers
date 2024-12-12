package com.chenkuan.watchers.cache.builder.cache;

import com.chenkuan.watchers.cache.cache.AbstractCaffeineCacheProxy;
import com.chenkuan.watchers.cache.factory.CacheExampleBuilderFactory;
import com.chenkuan.watchers.cache.watchers.CacheConfig;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author chenkuan
 **/

@Component
public class CaffeineCacheBuilder implements CacheExampleBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() {
        CacheExampleBuilderFactory.register(AbstractCaffeineCacheProxy.class, this);
    }

    @Override
    public Object buildCache(CacheConfig cacheConfig) {
        return Caffeine.newBuilder()
                .expireAfterWrite(cacheConfig.getExpireAfterWrite(), cacheConfig.getTimeUnit())
                // 初始的缓存空间大小
                .initialCapacity(cacheConfig.getInitializeSize())
                // 缓存的最大条数
                .maximumSize(cacheConfig.getMaxSize())
                .build();
    }

}
