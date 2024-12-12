package com.chenkuan.watchers.mvctest;

import com.chenkuan.watchers.cache.watchers.CacheConfig;
import com.chenkuan.watchers.common.CommonUtil;
import com.github.benmanes.caffeine.cache.Cache;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author chenkuan
 */

@Slf4j
@RestController
@RequestMapping("/cache")
public class WatchersController {

    @Resource(name = "caffeineCache03")
    private Cache<String, String> caffeine;

    @Resource
    private WatchersService watchersService;


    @PostMapping("/get")
    public BaseResp<?> get(@RequestBody Map<String, Object> map) {
        String key = CommonUtil.getParam(map, "key", String.class);
        return BaseResp.success(caffeine.getIfPresent(key));
    }

    @PostMapping("/getAll")
    public BaseResp<?> getAll() {
        return BaseResp.success(caffeine.asMap());
    }


    @PostMapping("/put")
    public BaseResp<?> put(@RequestBody Map<String, Object> map) {
        String key = CommonUtil.getParam(map, "key", String.class);
        String value = CommonUtil.getParam(map, "value", String.class);
        caffeine.put(key, value);
        return BaseResp.success();
    }

    @PostMapping("/invalidateAll")
    public BaseResp<?> invalidateAll() {
        caffeine.invalidateAll();
        return BaseResp.success();
    }

    @PostMapping("/watchers/get")
    public BaseResp<?> getDashboard(@RequestBody Map<String, Object> map) {
        String name = CommonUtil.getParam(map, "name", String.class);
        return BaseResp.success(watchersService.getDashboard(name));
    }

    @PostMapping("/watchers/getAll")
    public BaseResp<?> getDashboard() {
        return BaseResp.success(watchersService.getDashboard());
    }

    @PostMapping("/replace")
    public BaseResp<?> replace(@RequestBody CacheConfig cacheConfig) {
        watchersService.replaceCache(cacheConfig.getCacheName(), cacheConfig);
        return BaseResp.success();
    }
}
