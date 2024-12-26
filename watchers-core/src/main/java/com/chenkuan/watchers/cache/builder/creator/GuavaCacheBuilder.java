package com.chenkuan.watchers.cache.builder.creator;

import com.chenkuan.watchers.CacheExampleBuilder;
import com.chenkuan.watchers.CacheExampleBuilderFactory;
import com.chenkuan.watchers.cache.cache.AbstractGuavaCacheProxy;
import com.chenkuan.watchers.CacheConfig;
import com.google.common.cache.CacheBuilder;
import org.springframework.beans.factory.InitializingBean;

import java.util.concurrent.TimeUnit;

/**
 * @author chenkuan
 **/

public class GuavaCacheBuilder implements CacheExampleBuilder, InitializingBean {

    @Override
    public void afterPropertiesSet() {
        CacheExampleBuilderFactory.register(AbstractGuavaCacheProxy.class, this);
    }

    @Override
    public Object buildCache(CacheConfig cacheConfig) {
        return CacheBuilder.newBuilder()
                // 设置写入后过期时间
                .expireAfterWrite(cacheConfig.getExpireAfterWrite().toSeconds(), TimeUnit.SECONDS)
                // 设置初始容量
                .initialCapacity(cacheConfig.getInitializeSize())
                // 设置缓存的最大条目数
                .maximumSize(cacheConfig.getMaxSize())
                .build();
    }

}
