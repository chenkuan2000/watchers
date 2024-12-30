package com.chenkuan.watchers.cache.aspect;

import java.lang.annotation.*;

/**
 * @author chenkuan
 */


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Watch {

    String name() default "";


}
