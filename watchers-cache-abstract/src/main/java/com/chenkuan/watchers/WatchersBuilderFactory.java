package com.chenkuan.watchers;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkuan
 */

public class WatchersBuilderFactory {

    private static final Map<Class<?>, Object> CREATOR_MAP = new HashMap<>();

    public static void register(Class<?> clazz, Object creator){
        CREATOR_MAP.put(clazz,creator);
    }

    public static Object get(Class<?> clazz) {

        for (Map.Entry<Class<?>, Object> entry : CREATOR_MAP.entrySet()) {
                if (entry.getKey().isAssignableFrom(clazz)) {
                return entry.getValue();
            }
        }

        return null;
    }

}
