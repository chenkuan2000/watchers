package com.chenkuan.watchers.cache.watchers;


import com.chenkuan.watchers.CacheConfig;
import com.chenkuan.watchers.*;


import org.jetbrains.annotations.NotNull;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author chenkuan
 */

public class WatchersRegistrar implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(@NotNull ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static void createWatchers(String beanName, Object cacheBean, Class<?> clazz) {

        createWatchers(beanName, cacheBean, null, clazz);

    }

    private static void createWatchers(String beanName, Object cacheBean, CacheConfig cacheConfig, Class<?> clazz) {
        WatchersBuilder watchersBuilder = (WatchersBuilder) WatchersBuilderFactory.get(clazz);

        if (watchersBuilder == null) {
            return;
        }

        Watchers watchers = watchersBuilder.buildWatchers(beanName, cacheBean, cacheConfig);
        WatchersFactory.registerCacheWatchers(beanName, watchers);
    }

    private Object createCacheExample(Class<?> clazz, CacheConfig cacheConfig) {
        CacheExampleBuilder cacheExampleBuilder = (CacheExampleBuilder) CacheExampleBuilderFactory.get(clazz);
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
