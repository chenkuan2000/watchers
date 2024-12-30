package com.chenkuan.watchers.cache;

import com.chenkuan.watchers.Caffeine2WatchersBuilder;
import com.chenkuan.watchers.Caffeine3WatchersBuilder;
import com.chenkuan.watchers.Guava21WatchersBuilder;
import com.chenkuan.watchers.cache.api.WatchersManager;
import com.chenkuan.watchers.cache.aspect.WatchAspect;
import com.chenkuan.watchers.cache.builder.creator.CaffeineCacheBuilder;
import com.chenkuan.watchers.cache.builder.creator.GuavaCacheBuilder;
import com.chenkuan.watchers.cache.watchers.WatchersRegistrar;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.cache.CacheBuilder;
import org.junit.jupiter.api.Order;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

/**
 * @author chenkuan
 */


@Configuration
public class ComponentAutoConfiguration {

    @Order(1)
    @ConditionalOnClass(Caffeine.class)
    @Bean("caffeineCacheBuilder")
    public CaffeineCacheBuilder caffeineCacheBuilder() {
        return new CaffeineCacheBuilder();
    }

    @Order(1)
    @DependsOn("caffeineCacheBuilder")
    @ConditionalOnClass(Caffeine.class)
    @ConditionalOnBean(name = "feature2")
    @Bean("caffeineWatchersBuilder")
    public Caffeine2WatchersBuilder caffeine2WatchersBuilder() {
        return new Caffeine2WatchersBuilder();
    }

    @Order(1)
    @DependsOn("caffeineCacheBuilder")
    @ConditionalOnClass(Caffeine.class)
    @ConditionalOnBean(name = "feature3")
    @Bean("caffeineWatchersBuilder")
    public Caffeine3WatchersBuilder caffeine3WatchersBuilder() {
        return new Caffeine3WatchersBuilder();
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
    public Guava21WatchersBuilder guava21WatchersBuilder() {
        return new Guava21WatchersBuilder();
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
    @DependsOn({"caffeineWatchersBuilder", "guavaWatchersBuilder"})
    @Bean("watchAspect")
    public WatchAspect watchAspect() {
        return new WatchAspect();
    }

}
