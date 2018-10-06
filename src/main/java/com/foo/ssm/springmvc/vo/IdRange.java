package com.foo.ssm.springmvc.vo;

/**
 * Version 1.0.0
 * Created by foolish on 16/11/29.
 */
public class IdRange {

    private Integer minId;
    private Integer maxId;

    public IdRange() {}
    public IdRange(Integer minId, Integer maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }

    public Integer getMinId() {
        return minId;
    }

    public void setMinId(Integer minId) {
        this.minId = minId;
    }

    public Integer getMaxId() {
        return maxId;
    }

    public void setMaxId(Integer maxId) {
        this.maxId = maxId;
    }
}
