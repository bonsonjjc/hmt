package com.zs.config;

public enum Code {
    SUCCESS("0000", "成功"),
    AUTH_FAIL("0001", "用户名或密码错误"),
    NO_LOGIN("0002", "未登录,请登录"),
    NO_EXITS("0003", "数据不存在"),
    SERVER("9999", "服务器异常,请联系管理员");
    String code;
    String msg;

    Code(String code, String msg) {
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

    public static Code getByCode(String code) {
        for (Code value : Code.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return Code.SERVER;
    }
}
