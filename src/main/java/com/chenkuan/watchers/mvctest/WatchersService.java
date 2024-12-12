package com.chenkuan.watchers.mvctest;


import com.chenkuan.watchers.cache.watchers.CacheConfig;

import java.util.List;


/**
 * @author chenkuan
 */


public interface WatchersService {

    CacheConfig getDashboard(String name);

    List<CacheConfig> getDashboard();

    void replaceCache(String cacheName, CacheConfig cacheConfig);

}
