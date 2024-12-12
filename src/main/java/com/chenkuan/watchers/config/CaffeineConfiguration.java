package com.chenkuan.watchers.config;

import com.chenkuan.watchers.cache.aspect.Watch;
import com.github.benmanes.caffeine.cache.*;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.index.qual.NonNegative;
import org.checkerframework.checker.nullness.qual.NonNull;
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
    @Bean(name = "caffeineCache01")
    public Cache<String, Object> caffeineCache01() {
        return Caffeine.newBuilder()
                .expireAfter(new Expiry<String, Object>() {
                    @Override
                    public long expireAfterCreate(@NonNull String key, @NonNull Object value, long remainTime) {
                        return TimeUnit.MINUTES.toNanos(10);
                    }

                    @Override
                    public long expireAfterUpdate(@NonNull String key, @NonNull Object value, long nowTime, @NonNegative long remainTime) {
                        return TimeUnit.MINUTES.toNanos(1);
                    }

                    @Override
                    public long expireAfterRead(@NonNull String key, @NonNull Object value, long nowTime, @NonNegative long remainTime) {
                        if (remainTime < TimeUnit.SECONDS.toNanos(30)) {
                            log.info("caffeine-log-51:缓存仅剩30秒，但仍被读取 key:{}", key);
                        }
                        return remainTime;
                    }
                })
                .removalListener((key, value, removalCause) -> {
                    switch (removalCause) {
                        case SIZE:
                            log.info("caffeine-log-01:达到上限！被驱逐的key:{}-value:{}", key, value);
                            break;
                        case EXPIRED:
                            log.info("caffeine-log-02:key过期！被驱逐的key:{}-value:{}", key, value);
                            break;
                    }
                })
                // 初始的缓存空间大小
                .initialCapacity(8000)
                // 缓存的最大条数
                .maximumSize(20000)
                .build();
    }

    @Watch()
    @Bean(name="caffeineCache03")
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
