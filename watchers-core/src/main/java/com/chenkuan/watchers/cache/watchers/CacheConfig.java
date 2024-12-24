package com.chenkuan.watchers.cache.watchers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author chenkuan
 */


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CacheConfig {

    private String cacheName;

    private Integer initializeSize;

    private Long maxSize;

    private Long expireAfterAccess;

    private Long expireAfterWrite;

    private TimeUnit timeUnit;

    private Boolean expireVariably;

}
