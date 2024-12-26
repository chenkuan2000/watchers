package com.chenkuan.watchers;


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkuan
 **/


public class CacheExampleBuilderFactory {

    private static final Map<Class<?>, Object> EXAMPLE_BUILDER_MAP = new HashMap<>();

    public static void register(Class<?> clazz, Object builder){
        EXAMPLE_BUILDER_MAP.put(clazz,builder);
    }

    public static Object get(Class<?> clazz) {

//        CacheExampleBuilder builder = null;
//        if (AbstractCaffeineCacheProxy.class.isAssignableFrom(clazz)){
//            builder = EXAMPLE_BUILDER_MAP.get(AbstractCaffeineCacheProxy.class);
//        }
//
//        if (AbstractGuavaCacheProxy.class.isAssignableFrom(clazz)){
//            builder = EXAMPLE_BUILDER_MAP.get(AbstractGuavaCacheProxy.class);
//        }

        return EXAMPLE_BUILDER_MAP.get(clazz);
    }


}
