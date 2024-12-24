package com.chenkuan.watchers.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author chenkuan
 */


public class CommonUtil {
    private static final Logger log = LoggerFactory.getLogger(CommonUtil.class);


    private static final Map<Class<?>, Function<String, ?>> CONVERTERS = new HashMap<>();
    static {
        CONVERTERS.put(Integer.class, Integer::parseInt);
        CONVERTERS.put(String.class, Function.identity());
        CONVERTERS.put(Long.class, Long::valueOf);
        CONVERTERS.put(Double.class, Double::parseDouble);
        CONVERTERS.put(Boolean.class, Boolean::parseBoolean);
    }

    public static <T> T getParam(Map<String, Object> map, String key, Class<T> clazz, Boolean required) {
        if (map.get(key) == null) {
            if (required) {
                log.error("Param [{}] must be required",key);
                throw buildServiceAssertedException(ErrorEnum.PARAM_ERROR.getCode(), "Param " + key + " must be required.");
            } else {
                return null;
            }
        } else {
            String param = map.get(key).toString();
            Function<String, ?> converter = CONVERTERS.get(clazz);
            return clazz.cast(converter.apply(param));
        }
    }

    public static <T> T getParam(Map<String, Object> map, String key, Class<T> clazz) {
        return getParam(map, key, clazz, true);
    }

    public static <T> List<T> getList(Map<String, Object> map, String key, Class<T> clazz) {
        return getList(map, key, clazz, true);
    }
    public static <T> List<T> getList(Map<String, Object> map, String key, Class<T> clazz, Boolean required) {
        if (map.get(key) == null) {
            if (required) {
                log.error("Param {} must be required", key);
                throw buildServiceAssertedException(ErrorEnum.PARAM_ERROR.getCode(), "Param " + key + " must be required.");
            } else {
                return null;
            }
        } else {
            String value = String.valueOf(map.get(key));
            if (clazz.equals(Long.class)) {
                return Arrays.stream(value.split(","))
                        .map(val -> clazz.cast(Long.valueOf(val.trim())))
                        .collect(Collectors.toList());
            } else if (clazz.equals(String.class)) {
                return Arrays.stream(value.split(","))
                        .map(val -> clazz.cast(val.trim()))
                        .collect(Collectors.toList());
            }else if(clazz.equals(Integer.class)) {
                return Arrays.stream(value.split(","))
                        .map(val -> clazz.cast(Integer.valueOf(val.trim())))
                        .collect(Collectors.toList());
            }
            else {
                throw new IllegalArgumentException("Unsupported class type: " + clazz.getName());
            }
        }
    }

    public static ServiceException buildServiceAssertedException(String code, String message){
        ServiceException serviceException = new ServiceException(message);
        serviceException.setCode(code);
        serviceException.setMsg(message);
        return serviceException;
    }

}



