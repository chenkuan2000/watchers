package com.chenkuan.watchers.common;


/**
 * @author chenkuan
 */


@SuppressWarnings("ALL")
public enum ErrorEnum {

    /**
     * 成功
     */
    SUCCESS("0", "success"),

    /**
     * 参数错误
     */
    PARAM_ERROR("1120002","param invalid"),

    ;



    private String code;
    private String msg;


    ErrorEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
