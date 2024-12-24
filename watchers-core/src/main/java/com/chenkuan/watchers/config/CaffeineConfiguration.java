package com.chenkuan.watchers.config;

import com.chenkuan.watchers.cache.aspect.Watch;
import com.github.benmanes.caffeine.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * @author chenkuan
 */
@Slf4j
@Configuration
public class CaffeineConfiguration {

    @Watch()
    @Bean(name="caffeineCache05")
    public Cache<String, String> caffeineCache03() {
        return Caffeine.newBuilder()
                .expireAfterWrite(50,TimeUnit.SECONDS)
                // 初始的缓存空间大小
                .initialCapacity(200)
                // 缓存的最大条数
                .maximumSize(500)
                .build();
    }

}
