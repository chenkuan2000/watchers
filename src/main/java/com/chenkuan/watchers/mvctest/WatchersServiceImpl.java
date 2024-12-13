package com.chenkuan.watchers.mvctest;

import com.chenkuan.watchers.cache.api.WatchersManager;
import com.chenkuan.watchers.cache.watchers.CacheConfig;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

/**
 * @author chenkuan
 */

@Service
public class WatchersServiceImpl implements WatchersService {

    @Resource
    private WatchersManager watchersManager;

    @Override
    public CacheConfig getDashboard(String name) {
        return watchersManager.getDashboard(name);
    }

    @Override
    public List<CacheConfig> getDashboard() {
        List<CacheConfig> dashboard = watchersManager.getDashboard();
        dashboard.sort(Comparator.comparing(CacheConfig::getCacheName));
        return dashboard;
    }

    @Override
    public void replaceCache(String cacheName,CacheConfig cacheConfig) {
        watchersManager.replaceCache(cacheName, cacheConfig);
    }
}
