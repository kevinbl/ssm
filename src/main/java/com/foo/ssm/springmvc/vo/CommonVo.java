package com.foo.ssm.springmvc.vo;

/**
 * Created by f on 16/12/15.
 */
@SuppressWarnings("all")
public class CommonVo<T> {
    private boolean ret;
    private String message;
    private T data;

    public CommonVo() {

    }

    public CommonVo(boolean ret, String message) {
        this.ret = ret;
        this.message = message;
    }

    public CommonVo(boolean ret, String message, T data) {
        this.ret = ret;
        this.message = message;
        this.data = data;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
