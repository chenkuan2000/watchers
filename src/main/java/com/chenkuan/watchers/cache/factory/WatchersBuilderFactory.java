package com.chenkuan.watchers.cache.factory;



import com.chenkuan.watchers.cache.builder.watchers.WatchersBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkuan
 */


public class WatchersBuilderFactory {

    private static final Map<Class<?>, WatchersBuilder> CREATOR_MAP = new HashMap<>();

    public static void register(Class<?> clazz, WatchersBuilder creator){
        CREATOR_MAP.put(clazz,creator);
    }

    public static WatchersBuilder get(Class<?> clazz) {
        for (Map.Entry<Class<?>, WatchersBuilder> entry : CREATOR_MAP.entrySet()) {
                if (entry.getKey().isAssignableFrom(clazz)) {
                return entry.getValue();
            }
        }

        return null;
    }

}
