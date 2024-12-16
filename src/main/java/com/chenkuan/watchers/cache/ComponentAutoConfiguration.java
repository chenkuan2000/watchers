package com.chenkuan.watchers.cache;

import com.chenkuan.watchers.cache.api.WatchersManager;
import com.chenkuan.watchers.cache.aspect.WatchAspect;
import com.chenkuan.watchers.cache.builder.cache.CaffeineCacheBuilder;
import com.chenkuan.watchers.cache.builder.cache.GuavaCacheBuilder;
import com.chenkuan.watchers.cache.builder.cache.test;
import com.chenkuan.watchers.cache.builder.watchers.CaffeineWatchersBuilder;
import com.chenkuan.watchers.cache.builder.watchers.GuavaWatchersBuilder;
import com.chenkuan.watchers.cache.watchers.WatchersRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenkuan
 */


@Configuration

public class ComponentAutoConfiguration {


    @Bean("testBuilder")
    public test test(){
        return new test();
    }


    @Bean("caffeineCacheBuilder")
    public CaffeineCacheBuilder caffeineCacheBuilder() {
        return new CaffeineCacheBuilder();
    }

    @Bean("caffeineWatchersBuilder")
    public CaffeineWatchersBuilder caffeineWatchersBuilder() {
        return new CaffeineWatchersBuilder();
    }

    @Bean("guavaCacheBuilder")
    public GuavaCacheBuilder guavaCacheBuilder() {
        return new GuavaCacheBuilder();
    }

    @Bean("guavaWatchersBuilder")
    public GuavaWatchersBuilder guavaWatchersBuilder() {
        return new GuavaWatchersBuilder();
    }

    @Bean("watchersManager")
    public WatchersManager watchersManager(WatchersRegistrar watchersRegistrar) {
        return new WatchersManager(watchersRegistrar);
    }

    @Bean("watchersRegistrar")
    public WatchersRegistrar watchersRegistrar() {
        return new WatchersRegistrar();
    }

    @Bean("watchAspect")
    public WatchAspect watchAspect() {
        return new WatchAspect();
    }
}
