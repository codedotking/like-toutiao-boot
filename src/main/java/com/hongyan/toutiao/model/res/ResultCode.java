package com.hongyan.toutiao.model.res;


import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS("200", "成功"),
    FAIL("400", "参数异常"),
    INTERNAL_ERROR("500", "服务器内部异常");

    private final String code;
    private final String msg;

    ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
