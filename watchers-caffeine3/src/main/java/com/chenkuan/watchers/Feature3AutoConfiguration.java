package com.chenkuan.watchers;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.Policy;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author chenkuan
 */


@Configuration
public class Feature3AutoConfiguration {

    @Order(1)
    @ConditionalOnClass({Caffeine.class, Policy.FixedRefresh.class})
    @Bean("feature3")
    public Feature3 feature2() {
        return new Feature3();
    }

}
