package com.chenkuan.watchers.cache;

import com.chenkuan.watchers.cache.api.WatchersManager;
import com.chenkuan.watchers.cache.aspect.WatchAspect;
import com.chenkuan.watchers.cache.builder.cache.CaffeineCacheBuilder;
import com.chenkuan.watchers.cache.builder.cache.GuavaCacheBuilder;
import com.chenkuan.watchers.cache.builder.cache.test;
import com.chenkuan.watchers.cache.builder.watchers.CaffeineWatchersBuilder;
import com.chenkuan.watchers.cache.builder.watchers.GuavaWatchersBuilder;
import com.chenkuan.watchers.cache.watchers.WatchersRegistrar;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Policy;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Order;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author chenkuan
 */


@Configuration
public class ComponentAutoConfiguration {


    @Bean("testBuilder")
    public test test(){
        return new test();
    }


    @Order(1)
    @ConditionalOnClass({Caffeine.class, Policy.class})
    @Bean("caffeineCacheBuilder")
    public CaffeineCacheBuilder caffeineCacheBuilder() {
        return new CaffeineCacheBuilder();
    }

    @Order(1)
    @DependsOn("caffeineCacheBuilder")
    @ConditionalOnClass({Caffeine.class, Policy.class})
    @Bean("caffeineWatchersBuilder")
    public CaffeineWatchersBuilder caffeineWatchersBuilder() {
        return new CaffeineWatchersBuilder();
    }

    @Order(1)
    @ConditionalOnClass(CacheBuilder.class)
    @Bean("guavaCacheBuilder")
    public GuavaCacheBuilder guavaCacheBuilder() {
        return new GuavaCacheBuilder();
    }

    @Order(1)
    @DependsOn("guavaCacheBuilder")
    @ConditionalOnClass(CacheBuilder.class)
    @Bean("guavaWatchersBuilder")
    public GuavaWatchersBuilder guavaWatchersBuilder() {
        return new GuavaWatchersBuilder();
    }

    @Order(1)
    @DependsOn("watchersRegistrar")
    @Bean("watchersManager")
    public WatchersManager watchersManager(WatchersRegistrar watchersRegistrar) {
        return new WatchersManager(watchersRegistrar);
    }

    @Order(1)
    @Bean("watchersRegistrar")
    public WatchersRegistrar watchersRegistrar() {
        return new WatchersRegistrar();
    }

    @Order(1)
    @DependsOn({"caffeineWatchersBuilder","guavaWatchersBuilder"})
    @Bean("watchAspect")
    public WatchAspect watchAspect() {
        return new WatchAspect();
    }
}
