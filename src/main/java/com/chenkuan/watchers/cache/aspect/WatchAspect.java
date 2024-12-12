package com.chenkuan.watchers.cache.aspect;

import com.chenkuan.watchers.cache.cache.CaffeineCacheProxy;
import com.chenkuan.watchers.cache.cache.CaffeineGuavaProxy;
import com.chenkuan.watchers.cache.watchers.WatchersRegistrar;
import com.github.benmanes.caffeine.cache.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * @author chenkuan
 */


@Aspect
@Component
public class WatchAspect {

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


        if (com.github.benmanes.caffeine.cache.Cache.class.isAssignableFrom(bean.getClass())) {
            cacheProxy = new CaffeineCacheProxy((Cache<?, ?>) bean);
        }

        if (com.google.common.cache.Cache.class.isAssignableFrom(bean.getClass())){
            cacheProxy = new CaffeineGuavaProxy((com.google.common.cache.Cache<?,?>) bean);
        }

        return cacheProxy;
    }


}
