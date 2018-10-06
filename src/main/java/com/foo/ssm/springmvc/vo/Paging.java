package com.foo.ssm.springmvc.vo;

import java.io.Serializable;

/**
 * version 1.0.0
 * Created by foolish on 16/8/30 下午10:01.
 */
public class Paging implements Serializable {

    private static final long serialVersionUID = -6158600685706074541L;

    private int pageIndex;

    private int limit;

    public Paging() {
    }

    public Paging(int pageIndex,int limit){
        this.pageIndex=pageIndex;
        this.limit=limit;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "pageIndex=" + pageIndex +
                ", limit=" + limit +
                '}';
    }
}
