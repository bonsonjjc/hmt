package com.zs.comm;

import com.zs.config.Code;

public class Respond {
    private String code;
    private String msg;
    private Object data;

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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Respond success(Object data) {
        return custom(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(), data);
    }

    public static Respond success(String data) {
        return custom(Code.SUCCESS.getCode(), data, null);
    }

    public static Respond success() {
        return custom(Code.SUCCESS.getCode(), Code.SUCCESS.getMsg(), null);
    }

    public static Respond success(String msg, Object data) {
        return custom(Code.SUCCESS.getCode(), msg, data);
    }

    public static Respond custom(String code, String msg, Object data) {
        Respond result = new Respond();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    public static Respond error(String code, String msg) {
        return custom(code, msg, null);
    }

    public static Respond error(Code code) {
        return error(code.getCode(), code.getMsg());
    }
}
