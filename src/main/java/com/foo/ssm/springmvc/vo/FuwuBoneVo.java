package com.foo.ssm.springmvc.vo;

/**
 * 前端FuwuBone模版的统一json格式
 *
 * @author foolish
 * @version v1.0.0
 * @since 2017/4/1
 */
public class FuwuBoneVo<T> {
    private boolean ret;
    private int errcode = 0;
    private String errmsg = "";
    private int ver;
    private T data;

    private FuwuBoneVo() {
    }

    public static <T> FuwuBoneVo buildSuccess(T data) {
        FuwuBoneVo<Object> objectFuwuBoneVo = new FuwuBoneVo<>();
        objectFuwuBoneVo.ret = true;
        objectFuwuBoneVo.data = data;
        return objectFuwuBoneVo;
    }

    public static <T> FuwuBoneVo buildFailure() {
        FuwuBoneVo<Object> objectFuwuBoneVo = new FuwuBoneVo<>();
        objectFuwuBoneVo.ret = false;
        return objectFuwuBoneVo;
    }

    public static <T> FuwuBoneVo buildFailure(String errmsg) {
        FuwuBoneVo<Object> objectFuwuBoneVo = new FuwuBoneVo<>();
        objectFuwuBoneVo.ret = false;
        objectFuwuBoneVo.errmsg = errmsg;
        return objectFuwuBoneVo;
    }

    public static <T> FuwuBoneVo buildFailure(String errmsg, int errcode) {
        FuwuBoneVo<Object> objectFuwuBoneVo = new FuwuBoneVo<>();
        objectFuwuBoneVo.ret = false;
        objectFuwuBoneVo.errcode = errcode;
        objectFuwuBoneVo.errmsg = errmsg;
        return objectFuwuBoneVo;
    }

    public boolean getRet() {
        return ret;
    }

    public FuwuBoneVo setRet(boolean ret) {
        this.ret = ret;
        return this;
    }

    public int getErrcode() {
        return errcode;
    }

    public FuwuBoneVo setErrcode(int errcode) {
        this.errcode = errcode;
        return this;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public FuwuBoneVo setErrmsg(String errmsg) {
        this.errmsg = errmsg;
        return this;
    }

    public int getVer() {
        return ver;
    }

    public FuwuBoneVo setVer(int ver) {
        this.ver = ver;
        return this;
    }

    public T getData() {
        return data;
    }

    public FuwuBoneVo setData(T data) {
        this.data = data;
        return this;
    }
}
