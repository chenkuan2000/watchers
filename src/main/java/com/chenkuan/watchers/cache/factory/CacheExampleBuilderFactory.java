package com.chenkuan.watchers.cache.factory;

import com.chenkuan.watchers.cache.builder.cache.CacheExampleBuilder;
import com.chenkuan.watchers.cache.cache.AbstractCaffeineCacheProxy;
import com.chenkuan.watchers.cache.cache.AbstractGuavaCacheProxy;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author chenkuan
 **/


public class CacheExampleBuilderFactory {

    private static final Map<Class<?>, CacheExampleBuilder> EXAMPLE_BUILDER_MAP = new HashMap<>();

    public static void register(Class<?> clazz, CacheExampleBuilder builder){
        EXAMPLE_BUILDER_MAP.put(clazz,builder);
    }

    public static CacheExampleBuilder get(Class<?> clazz) {

        CacheExampleBuilder builder = null;
        if (AbstractCaffeineCacheProxy.class.isAssignableFrom(clazz)){
            builder = EXAMPLE_BUILDER_MAP.get(AbstractCaffeineCacheProxy.class);
        }

        if (AbstractGuavaCacheProxy.class.isAssignableFrom(clazz)){
            builder = EXAMPLE_BUILDER_MAP.get(AbstractGuavaCacheProxy.class);
        }

        return builder;
    }


}
