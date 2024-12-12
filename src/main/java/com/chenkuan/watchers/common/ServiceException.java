package com.chenkuan.watchers.common;//


import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkuan
 */


public class ServiceException extends RuntimeException {
    private String code;
    private String msg;
    private Object data;

    public ServiceException() {
    }

    public ServiceException(String code, String msg) {
        this.code = code;
        this.msg = msg;
        this.data = null;
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return this.data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Map<String, Object> map() {
        Map<String, Object> map = new HashMap(1);
        map.put("code", this.getCode());
        map.put("msg", this.getMsg());
        map.put("data", (Object)null);
        return map;
    }
}
