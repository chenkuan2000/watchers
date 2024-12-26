package com.chenkuan.watchers;

import com.chenkuan.watchers.Caffeine2WatchersBuilder;
import com.github.benmanes.caffeine.base.UnsafeAccess;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author chenkuan
 */


@Configuration
public class Feature2AutoConfiguration {

    @Order(1)
    @ConditionalOnClass({Caffeine.class, UnsafeAccess.class})
    @Bean("feature2")
    public Feature2 feature2() {
        return new Feature2();
    }

}
