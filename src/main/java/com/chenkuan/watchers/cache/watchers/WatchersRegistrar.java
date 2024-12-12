package com.chenkuan.watchers.cache.watchers;



import com.chenkuan.watchers.cache.builder.cache.CacheExampleBuilder;
import com.chenkuan.watchers.cache.builder.watchers.WatchersBuilder;
import com.chenkuan.watchers.cache.cache.AbstractCacheProxy;
import com.chenkuan.watchers.cache.factory.CacheExampleBuilderFactory;
import com.chenkuan.watchers.cache.factory.WatchersBuilderFactory;
import com.chenkuan.watchers.cache.factory.WatchersFactory;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author chenkuan
 */


@Component
public class WatchersRegistrar {

    @Resource
    private ApplicationContext applicationContext;

    public static void createWatchers(String beanName, Object cacheBean, Class<?> clazz) {

        createWatchers(beanName, cacheBean, null, clazz);

    }

    private static void createWatchers(String beanName, Object cacheBean, CacheConfig cacheConfig, Class<?> clazz) {
        WatchersBuilder watchersBuilder = WatchersBuilderFactory.get(clazz);

        if (watchersBuilder == null) {
            return ;
        }

        Watchers watchers = watchersBuilder.buildWatchers(beanName, cacheBean, cacheConfig);
        WatchersFactory.registerCacheWatchers(beanName, watchers);
    }

    private static Object createCacheExample(Class<?> clazz,CacheConfig cacheConfig){
        CacheExampleBuilder cacheExampleBuilder = CacheExampleBuilderFactory.get(clazz);
        return cacheExampleBuilder.buildCache(cacheConfig);
    }

    public void replaceCache(String cacheName, CacheConfig cacheConfig) {

        if (existsWatchers(cacheName)) {
            removeWatchers(cacheName);
        }

        Class<?> clazz = applicationContext.getType(cacheName);

        Object cache = createCacheExample(clazz, cacheConfig);
        createWatchers(cacheName, cache, cacheConfig, clazz);

        DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) applicationContext.getAutowireCapableBeanFactory();

        if (beanFactory.containsBean(cacheName)) {
            Object bean = beanFactory.getBean(cacheName);
            AbstractCacheProxy cacheProxy = (AbstractCacheProxy) bean;
            cacheProxy.replaceCache(cache);
        }

    }


    /**
     * 移除CacheWatchers
     */
    public void removeWatchers(String cacheName) {
        WatchersFactory.removeCacheWatchers(cacheName);
    }


    /**
     * 获取CacheWatchers
     */
    public Map<String, Watchers> getWatchers() {
        return WatchersFactory.getCacheWatchers();
    }

    /**
     * 获取CacheWatchers
     */
    public Watchers getWatchers(String cacheName) {
        return WatchersFactory.getCacheWatchers(cacheName);
    }

    /**
     * 判断CacheWatchers是否存在
     */
    public boolean existsWatchers(String cacheName) {
        return WatchersFactory.getCacheWatchers(cacheName) != null;
    }


}
