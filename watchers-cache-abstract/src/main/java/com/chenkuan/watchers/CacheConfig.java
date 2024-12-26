package com.chenkuan.watchers;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * @author chenkuan
 */
public class CacheConfig {

    private String cacheName;

    private Integer initializeSize;

    private Long maxSize;

    private Duration expireAfterAccess;

    private Duration expireAfterWrite;

    private TimeUnit timeUnit;

    private Boolean expireVariably;

    public CacheConfig() {
    }

    private CacheConfig(String cacheName, Integer initializeSize, Long maxSize, Duration expireAfterAccess, Duration expireAfterWrite, TimeUnit timeUnit, Boolean expireVariably) {
        this.cacheName = cacheName;
        this.initializeSize = initializeSize;
        this.maxSize = maxSize;
        this.expireAfterAccess = expireAfterAccess;
        this.expireAfterWrite = expireAfterWrite;
        this.timeUnit = timeUnit;
        this.expireVariably = expireVariably;
    }

    public String getCacheName() {
        return cacheName;
    }

    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;
    }

    public Integer getInitializeSize() {
        return initializeSize;
    }

    public void setInitializeSize(Integer initializeSize) {
        this.initializeSize = initializeSize;
    }

    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public Duration getExpireAfterAccess() {
        return expireAfterAccess;
    }

    public void setExpireAfterAccess(Duration expireAfterAccess) {
        this.expireAfterAccess = expireAfterAccess;
    }

    public Duration getExpireAfterWrite() {
        return expireAfterWrite;
    }

    public void setExpireAfterWrite(Duration expireAfterWrite) {
        this.expireAfterWrite = expireAfterWrite;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnit timeUnit) {
        this.timeUnit = timeUnit;
    }

    public Boolean getExpireVariably() {
        return expireVariably;
    }

    public void setExpireVariably(Boolean expireVariably) {
        this.expireVariably = expireVariably;
    }

    public static CacheConfigBuilder builder() {
        return new CacheConfigBuilder();
    }

    public static class CacheConfigBuilder {
        private String cacheName;
        private Integer initializeSize;
        private Long maxSize;
        private Duration expireAfterAccess;
        private Duration expireAfterWrite;
        private TimeUnit timeUnit;
        private Boolean expireVariably;

        CacheConfigBuilder() {
        }

        public CacheConfigBuilder cacheName(String cacheName) {
            this.cacheName = cacheName;
            return this;
        }

        public CacheConfigBuilder initializeSize(Integer initializeSize) {
            this.initializeSize = initializeSize;
            return this;
        }

        public CacheConfigBuilder maxSize(Long maxSize) {
            this.maxSize = maxSize;
            return this;
        }

        public CacheConfigBuilder expireAfterAccess(Duration expireAfterAccess) {
            this.expireAfterAccess = expireAfterAccess;
            return this;
        }

        public CacheConfigBuilder expireAfterWrite(Duration expireAfterWrite) {
            this.expireAfterWrite = expireAfterWrite;
            return this;
        }

        public CacheConfigBuilder timeUnit(TimeUnit timeUnit) {
            this.timeUnit = timeUnit;
            return this;
        }

        public CacheConfigBuilder expireVariably(Boolean expireVariably) {
            this.expireVariably = expireVariably;
            return this;
        }

        public CacheConfig build() {
            return new CacheConfig(cacheName, initializeSize, maxSize, expireAfterAccess, expireAfterWrite, timeUnit, expireVariably);
        }
    }
}
