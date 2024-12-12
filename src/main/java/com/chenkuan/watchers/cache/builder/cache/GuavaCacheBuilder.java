package com.chenkuan.watchers.cache.builder.cache;

import com.chenkuan.watchers.cache.cache.AbstractGuavaCacheProxy;
import com.chenkuan.watchers.cache.factory.CacheExampleBuilderFactory;
import com.chenkuan.watchers.cache.watchers.CacheConfig;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author chenkuan
 **/

@Component
public class GuavaCacheBuilder implements CacheExampleBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() {
        CacheExampleBuilderFactory.register(AbstractGuavaCacheProxy.class, this);

    }

    @Override
    public Object buildCache(CacheConfig cacheConfig) {
        return CacheBuilder.newBuilder()
                // 设置写入后过期时间
                .expireAfterWrite(cacheConfig.getExpireAfterWrite(), cacheConfig.getTimeUnit())
                // 设置初始容量
                .initialCapacity(cacheConfig.getInitializeSize())
                // 设置缓存的最大条目数
                .maximumSize(cacheConfig.getMaxSize())
                .build();
    }

}
