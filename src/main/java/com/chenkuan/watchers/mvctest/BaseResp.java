package com.chenkuan.watchers.mvctest;

import com.chenkuan.watchers.common.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author chenkuan
 */


@Data
public class BaseResp<T> implements Serializable {

    private String code;
    private String msg;
    private T data;


    public static <T> BaseResp<T> success(T obj) {
        BaseResp<T> result = new BaseResp<>();
        result.setCode(ErrorEnum.SUCCESS.getCode());
        result.setMsg(ErrorEnum.SUCCESS.getMsg());
        result.setData(obj);
        return result;
    }

    public static BaseResp<Void> success() {
        BaseResp<Void> result = new BaseResp<>();
        result.setCode(ErrorEnum.SUCCESS.getCode());
        result.setMsg(ErrorEnum.SUCCESS.getMsg());
        return result;
    }
}