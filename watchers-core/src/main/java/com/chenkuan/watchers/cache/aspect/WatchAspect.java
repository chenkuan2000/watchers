package com.chenkuan.watchers.cache.aspect;

import com.chenkuan.watchers.Caffeine2CacheProxy;
import com.chenkuan.watchers.Caffeine3CacheProxy;
import com.chenkuan.watchers.cache.watchers.WatchersRegistrar;
import com.github.benmanes.caffeine.cache.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author chenkuan
 */


@Aspect
public class WatchAspect {

    private static final Logger log = LoggerFactory.getLogger(WatchAspect.class);

    @Pointcut("@annotation(com.chenkuan.watchers.cache.aspect.Watch)")
    public void pointCut() {
    }

    @Around("@annotation(watch)")
    public Object aroundWatchAnnotation(ProceedingJoinPoint joinPoint, Watch watch) throws Throwable {

        String beanName = watch.name();
        if (beanName.isEmpty()) {
            beanName = joinPoint.getSignature().getName();
        }

        Object bean = joinPoint.proceed();

        WatchersRegistrar.createWatchers(beanName, bean, bean.getClass());

        Object cacheProxy = null;


        try {
            // Caffeine 2.x 特有类
            Class.forName("com.github.benmanes.caffeine.base.UnsafeAccess");
            cacheProxy = new Caffeine2CacheProxy((Cache<?, ?>) bean);
            log.info("Caffeine 2.x");
        } catch (ClassNotFoundException e1) {
            try {
                // Caffeine 3.x 共用类
                Class.forName("com.github.benmanes.caffeine.cache.Policy$FixedRefresh");
                cacheProxy = new Caffeine3CacheProxy((Cache<?, ?>) bean);
                log.info("Caffeine 3.x");
            } catch (ClassNotFoundException e2) {
                cacheProxy = bean;
                log.info("找不到指定的版本的Caffeine");
            }
        }

        return cacheProxy;
    }

}
